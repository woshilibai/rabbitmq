package com.example.rabbitmqconsumer.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Description: 基于延迟插件演示
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:58
 */
@Component
@Slf4j
public class DelayedConsumer {

    @RabbitListener(queues = "delayedQueue")
    public void process(Channel channel, Message message) throws IOException {
        log.info("接收到队列delayedQueue的消息内容为：{}，时间为：{}",new String(message.getBody()), new Date().toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
