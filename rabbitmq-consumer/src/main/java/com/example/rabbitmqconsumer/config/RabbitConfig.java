package com.example.rabbitmqconsumer.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitTemplate自定义处理
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 11:34
 */
@Configuration
public class RabbitConfig {

//    @Bean
//    public MessageConverter getMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
}
