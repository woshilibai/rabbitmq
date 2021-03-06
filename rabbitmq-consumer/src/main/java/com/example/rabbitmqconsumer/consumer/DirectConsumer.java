package com.example.rabbitmqconsumer.consumer;

import com.example.rabbitmqconsumer.model.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:58
 */
@Component
@Slf4j
public class DirectConsumer {

    /**
     * 【队列内的一个消息只能有一个消费者】，集群部署下，多个消费者同属于一个消费者组，消息只能被消费者组中的一个消费者进行消费，多个消费者会轮询消费队列中的消息
     * 消费者组内的消费者彼此是竞争关系，排他的消费消息
     * 其他交换机同上，或者说与交换机无关，
     */
    @RabbitListener(queues = "myDirectQueue")
    public void process(Channel channel, Message msg) throws IOException {
        log.info("接收到队列myDirectQueue的消息内容为：{}", new String(msg.getBody()));
        //  手动确认，如果没有确认，消息会驻留在队列内不会被删除
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);

        //  拒收并且重新入队导致会一直循环消费
//        channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
    }

    /**
     * 生产者设置MessageConverter，如果消费者在消费消息时的接受参数为object，则也要设置同一个MessageConverter，
     * 因为默认的是SimpleMessageConverter，会转换出错
     * @param user
     */
//    @RabbitListener(queues = "myDirectQueue")
//    public void process(User user) {
//        log.info("接收到队列myDirectQueue的消息内容为：{}", user);
//    }
}
