package com.example.rabbitmqconsumer.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Description: 死信队列消费端
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/25 10:18
 */
@Slf4j
@Component
public class DlxConsumer {

    /**
     * 模拟消费普通业务队列时出现拒签情况，如果是延迟队列场景，一般业务队列会不需要消费者，静静等待TTL过期变为死信，然后消费即可
     * @param channel
     * @param message
     */
//    @RabbitListener(queues = "queue.normal")
//    public void processNormal(Channel channel, Message message) throws IOException {
//        log.info("接收到普通业务队列queue.normal的消息内容为：{}，时间为：{}", new String(message.getBody()), new Date().toString());
//        log.info("模拟消费普通业务队列时出现拒签情况时，消息变为死信并进入死信队列...");
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//    }


    @RabbitListener(queues = "queue.dlx")
    public void processDLX(Channel channel, Message message) throws IOException {
        log.info("接收到死信队列queue.dlx的消息内容为：{}，时间为：{}", new String(message.getBody()), new Date().toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
