package com.example.rabbitmqprovider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitTemplate自定义处理
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 11:34
 */
@Slf4j
@Configuration
public class RabbitConfig {

    /**
     * 生产者设置MessageConverter，如果消费者在消费消息时的接受参数为object，则也要设置同一个MessageConverter，
     * 因为默认的是SimpleMessageConverter，会转换出错
     * @return
     */
//    @Bean
//    public MessageConverter getMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }


    /*
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //消息发送成功的回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("ConfirmCallback:     "+"相关数据："+correlationData);
            log.info("ConfirmCallback:     "+"确认情况："+ack);
            log.info("ConfirmCallback:     "+"原因："+cause);
        });

        //发生异常时的消息返回提醒
        rabbitTemplate.setReturnsCallback((returned) -> {
            log.info("交换机：{}，消息被退回：{},退回原因：{}",
                    returned.getExchange(),new String(returned.getMessage().getBody()),returned.getReplyText());
        });
        return rabbitTemplate;
    }
    */
}
