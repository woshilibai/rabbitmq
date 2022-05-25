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
public class FanoutConsumer {

    @RabbitListener(queues = "fanoutQueueA")
    public void processA(String msg){
        log.info("接收到队列fanoutQueueA的消息：{}", msg);
    }

    @RabbitListener(queues = "fanoutQueueB")
    public void processB(String msg){
        log.info("接收到队列fanoutQueueB的消息：{}", msg);
    }

    @RabbitListener(queues = "fanoutQueueC")
    public void processC(String msg){
        log.info("接收到队列fanoutQueueC的消息：{}", msg);
    }
}
