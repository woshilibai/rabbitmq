package com.example.rabbitmqprovider.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.rabbitmqprovider.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:42
 */
@Slf4j
@RestController
@RequestMapping(value = "/provider")
public class ProviderController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendByUser")
    public String sendByUser(){
        User user = new User(UUID.randomUUID().toString(),30, "田文龙");
        log.info("准备发送消息到direct交换机，消息为：{}", user);
//        rabbitTemplate.convertAndSend("myDirectExchange", "my.direct.routing", JSONUtil.toJsonStr(user));
        rabbitTemplate.convertAndSend("myDirectExchange", "my.direct.routing", user);
        return "success direct";
    }

    @GetMapping("/sendByDirect")
    public String sendByDirect(){
        String msg = "hello direct";
        log.info("准备发送消息到direct交换机，消息为：{}", msg);
        //  springboot的rabbitTemplate发送的消息默认都是持久化消息
        rabbitTemplate.convertAndSend("myDirectExchange", "my.direct.routing", msg);
        return "success direct";
    }

    @GetMapping("/sendByFanout")
    public String sendByFanout(){
        String msg = "hello fanout";
        log.info("准备发送消息到fanout交换机，消息为：{}", msg);
        rabbitTemplate.convertAndSend("myFanoutExchange", null, msg);
        return "success fanout";
    }

    @GetMapping("/sendByTopic")
    public String sendByTopic(){
        String msg = "hello topic";
        log.info("准备发送消息到topic交换机...");
        rabbitTemplate.convertAndSend("myTopicExchange", "red.green.key1", msg+ " [red.green.key1]");
        rabbitTemplate.convertAndSend("myTopicExchange", "red.green.key2.black", msg+ " [red.green.key2.black]");
        rabbitTemplate.convertAndSend("myTopicExchange", "key3.red", msg+ " [key3.red]");
        rabbitTemplate.convertAndSend("myTopicExchange", "red.key3.black", msg+ " [red.key3.black]");
        return "success topic";
    }
}
