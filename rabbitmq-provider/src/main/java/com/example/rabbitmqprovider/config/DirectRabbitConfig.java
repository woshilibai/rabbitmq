package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 直连交换机：根据message携带的rootKey，直接将消息转发到与交换机绑定路由为rootKey的队列
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 8:28
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 交换机声明
     */
    @Bean
    public DirectExchange myDirectExchange(){
        return new DirectExchange("myDirectExchange", true, false);
    }

    /**
     * 队列声明
     * @return
     */
    @Bean
    public Queue myDirectQueue(){
        return new Queue("myDirectQueue", true);
    }

    /**
     * 绑定声明
     * @return
     */
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(myDirectQueue()).to(myDirectExchange()).with("my.direct.routing");
    }

}
