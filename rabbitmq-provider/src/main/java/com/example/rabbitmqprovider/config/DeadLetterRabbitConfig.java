package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 死信队列，配合TTL可实现延迟队列，缺陷是延迟时间无法自定义，自定义时会存在延时时间短的消息不能优先消费的问题。
 * 可以采用rabbitmq延迟插件实现自定义ttl延迟队列
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/25 9:42
 */
@Configuration
public class DeadLetterRabbitConfig {

    //  普通业务交换机
    @Bean
    public DirectExchange normalExchange(){
        return new DirectExchange("exchange.normal", true, false);
    }

    //  普通业务队列
    @Bean
    public Queue normalQueue(){
        //  普通队列的消息变为死信时，重新转发到死信交换机
        Map<String, Object> arguments = new HashMap<>();
        //  设置死信转发的死信交换机
        arguments.put("x-dead-letter-exchange", "exchange.dlx");
        //  设置死信转发时携带的路由键，默认消息原本携带的
        arguments.put("x-dead-letter-routing-key", "routingkey.dlx");
        //  设置队列的最大长度，测试超过队列长度时消息变为死信场景
        arguments.put("x-max-length", 3);
        //  设置队列的TTL为10s
        arguments.put("x-message-ttl", new Long(10000));
        return new Queue("queue.normal", true,false, false, arguments);
    }

    //  绑定业务队列到业务交换机，路由键为routingkey.normal
    @Bean
    public Binding bindNormalQueue(Queue normalQueue, DirectExchange normalExchange){
        return BindingBuilder.bind(normalQueue).to(normalExchange).with("routingkey.normal");
    }


    //  死信交换机
    @Bean
    public DirectExchange dlxExchange(){
        return new DirectExchange("exchange.dlx", true, false);
    }

    //  死信队列
    @Bean
    public Queue dlxQueue(){
        return new Queue("queue.dlx", true);
    }

    //  绑定死信队列到死信交换机，路由键为routingkey.dlx
    @Bean
    public Binding bindDlxQueue(Queue dlxQueue, DirectExchange dlxExchange){
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("routingkey.dlx");
    }
}
