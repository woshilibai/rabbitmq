package com.example.rabbitmqconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 发布确认队列消费端
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:58
 */
@Component
@Slf4j
public class ConfirmConsumer {

    @RabbitListener(queues = "confirmQueue")
    public void process(String msg) {
        log.info("接收到队列confirmQueue的消息内容为：{}，时间为：{}", msg, new Date().toString());
    }
}
