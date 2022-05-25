package com.example.rabbitmqprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:42
 */
@Slf4j
@RestController
@RequestMapping(value = "/confirm/provider")
public class ConfirmProviderController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(){
        String msg = "hello confirm";
        log.info("准备发送消息到direct交换机，消息为：{}", msg);
        rabbitTemplate.convertAndSend("confirmExchange", "confirm.rootKey", msg);
        return "success confirm";
    }

    @GetMapping("/send_wrong_exchange")
    public String send_wrong_exchange(){
        String msg = "hello confirm";
        log.info("准备发送消息到direct交换机，消息为：{}", msg);
        rabbitTemplate.convertAndSend("confirmExchange" + 1, "confirm.rootKey", msg);
        return "success confirm";
    }

    //  实现ConfirmCallback后，生产者发送消息改造,
    //  可以把交换机改错，测试ConfirmCallback的回调，如果没有回调处理，消息在交换机会丢失
    //  可以把rootKey改错，测试ReturnCallback的回调，如果没有回调处理，消息在交换机会丢失
    @GetMapping("/sendWithConfirm")
    public String sendWithConfirm(){

        CorrelationData correlationData = new CorrelationData("1");
        String msg = "hello confirm";
        log.info("准备发送消息到direct交换机，消息为：{}", msg);
        rabbitTemplate.convertAndSend("confirmExchange", "confirm.rootKey", msg, correlationData);
        return "success confirm";
    }

}
