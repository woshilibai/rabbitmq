package com.example.rabbitmqprovider.controller;

import com.example.rabbitmqprovider.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 死信消息发送演示
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:42
 */
@Slf4j
@RestController
@RequestMapping(value = "/dlx/provider")
public class DlxProviderController {

    @Resource
    RabbitTemplate rabbitTemplate;


    @GetMapping("/send")
    public String send(){
        String msg = "hello dlx";
        log.info("准备发送消息到交换机，消息为：{}，发送时间：{}", msg, new Date().toString());
        //  正常发送消息到普通业务队列，消息变为死信后，会自动重新发送到死信交换机，进而转发到死信队列
        rabbitTemplate.convertAndSend("exchange.normal", "routingkey.normal", msg);
        return "success dlx";
    }

}
