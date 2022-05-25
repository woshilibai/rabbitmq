package com.example.rabbitmqprovider.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 10:58
 */
@Component
@Slf4j
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 将自定义ConfirmCallback注入到rabbitTemplate
     */
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);    //  确保消息成功路由到交换机，否则触发回调
        rabbitTemplate.setReturnsCallback(this);    //  确保消息成功路由到队列，否则触发回调
    }

    /**
     * 交换机确认回调方法
     * 1、发消息 交换机成功接收
     * 2、发消息 交换机接收失败
     * @param correlationData 回调消息的id以及相关信息
     * @param ack 是否收到消息
     * @param cause 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack){
            log.info("成功接收id为：{}",id);
        }else {
            log.info("接收消息失败id为：{},原因：{}",id,cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("交换机：{}，消息被退回：{},退回原因：{}",
                returned.getExchange(),new String(returned.getMessage().getBody()),returned.getReplyText());
    }
}
