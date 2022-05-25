package com.example.rabbitmqprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 基于延迟插件，延迟消息发送演示
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:42
 */
@Slf4j
@RestController
@RequestMapping(value = "/delayed/provider")
public class DelayedProviderController {

    @Resource
    RabbitTemplate rabbitTemplate;

    //  发送延迟自定义时间的消息
    @GetMapping("/send/{delayed}")
    public String send(@PathVariable("delayed") Integer delayed){
        String msg = "hello delayed";
        log.info("准备发送消息到交换机，消息为：{}，发送时间：{}", msg, new Date().toString());
        //  消息会在延迟交换机临时存放，直到ttl过期，再转发到队列，然后进行消费
        rabbitTemplate.convertAndSend("delayedExchange", "routingkey.delayed", msg, message ->{
                            //  单位ms
                            message.getMessageProperties().setDelay(delayed);
                            return message;});
        return "success delayed";
    }

    //  发送无延迟消息，当成普通交换机使用
    @GetMapping("/sendNoDelay")
    public String sendNoDelay(){
        String msg = "hello no delayed";
        log.info("准备发送消息到交换机，消息为：{}，发送时间：{}", msg, new Date().toString());
        rabbitTemplate.convertAndSend("delayedExchange", "routingkey.delayed", msg);
        return "success no delayed";
    }

}
