package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 主题交换机：队列与交换机的绑定关系为支持通配符，根据消息的rootKey进行规则匹配，然后转发到对应队列
 *                  * : 匹配一个单词
 *                  # : 匹配0个或多个单词
 *               功能涵盖直连交换机和扇出交换机，单词之间用"."分割
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 9:24
 */
@Configuration
public class TopicRabbitConfig {

    /**
     * 交换机声明
     * @return
     */
    @Bean
    public TopicExchange myTopicExchange(){
        return new TopicExchange("myTopicExchange", true, false);
    }

    /**
     * 队列声明
     * @return
     */
    @Bean
    public Queue myTopicQueue1(){
        return new Queue("myTopicQueue1", true);
    }

    @Bean
    public Queue myTopicQueue2(){
        return new Queue("myTopicQueue2", true);
    }

    /**
     * 绑定关系声明
     * @return
     */
    //  队列myTopicQueue1  ----------路由规则*.*.key1--------> 交换机myTopicExchange
    @Bean
    public Binding binging1(){
        return BindingBuilder.bind(myTopicQueue1()).to(myTopicExchange()).with("*.*.key1");
    }

    //  队列myTopicQueue2  ----------路由规则#.key2.*--------> 交换机myTopicExchange
    @Bean
    public Binding binging2(){
        return BindingBuilder.bind(myTopicQueue2()).to(myTopicExchange()).with("#.key2.*");
    }

    //  队列myTopicQueue2  ----------路由规则key3.#--------> 交换机myTopicExchange
    @Bean
    public Binding binging3(){
        return BindingBuilder.bind(myTopicQueue2()).to(myTopicExchange()).with("key3.#");
    }

}
