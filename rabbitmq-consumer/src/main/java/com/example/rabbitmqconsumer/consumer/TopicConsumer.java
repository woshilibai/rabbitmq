package com.example.rabbitmqconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 9:16
 */
@Slf4j
@Component
public class TopicConsumer {

    @RabbitListener(queues = "myTopicQueue1")
    public void processA(String msg){
        log.info("接收到队列myTopicQueue1的消息：{}", msg);
    }

    @RabbitListener(queues = "myTopicQueue2")
    public void processB(String msg){
        log.info("接收到队列myTopicQueue2的消息：{}", msg);
    }

}
