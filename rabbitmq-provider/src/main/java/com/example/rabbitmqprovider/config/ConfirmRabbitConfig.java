package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: rabbitmq发布确认机制
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 10:36
 */
@Configuration
public class ConfirmRabbitConfig {

    @Bean
    public DirectExchange confirmExchange(){
        return new DirectExchange("confirmExchange", true, false);
    }

    @Bean
    public Queue confirmQueue(){
        return new Queue("confirmQueue", true);
    }

    @Bean
    public Binding bind(DirectExchange confirmExchange,  Queue confirmQueue){
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with("confirm.rootKey");
    }
}
