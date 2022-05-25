package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 基于rabbitmq延迟插件实现延迟队列
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/25 11:07
 */
@Configuration
public class DelayedRabbitConfig {

    //  延迟交换机，会临时存放消息直到ttl过期再转发给队列， 根据发送消息时携带的delay进行延时，如果不带delay参数，则直接转发到队列
    @Bean
    public DirectExchange delayedExchange(){
        DirectExchange delayedExchange = new DirectExchange("delayedExchange", true, false);
        //  必须
        delayedExchange.setDelayed(true);
        return delayedExchange;
    }

    //  普通队列
    @Bean
    public Queue delayedQueue(){
        return new Queue("delayedQueue", true);
    }

    //  绑定
    @Bean
    public Binding bindDelayedQueue(Queue delayedQueue, DirectExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with("routingkey.delayed");
    }

}
