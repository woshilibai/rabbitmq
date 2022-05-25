package com.example.rabbitmqprovider.config;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 扇出交换机：交换机将接受到的message转发到所有绑定的队列，与rootKey无关，可以实现发布订阅模式
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 9:03
 */
@Configuration
public class FanoutRabbitConfig {

    /**
     * 交换机声明
     * @return
     */
    @Bean
    public FanoutExchange myFanoutExchange(){
        return new FanoutExchange("myFanoutExchange", true, false);
    }

    /**
     * 队列声明
     * @return
     */
    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanoutQueueA", true);
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanoutQueueB", true);
    }

    @Bean
    public Queue fanoutQueueC(){
        return new Queue("fanoutQueueC", true);
    }

    /**
     * 绑定声明
     * @return
     */
    @Bean
    public Binding bindingFanoutQueueA(){
        return BindingBuilder.bind(fanoutQueueA()).to(myFanoutExchange());
    }

    @Bean
    public Binding bindingFanoutQueueB(){
        return BindingBuilder.bind(fanoutQueueB()).to(myFanoutExchange());
    }

    @Bean
    public Binding bindingFanoutQueueC(){
        return BindingBuilder.bind(fanoutQueueC()).to(myFanoutExchange());
    }
}
