package com.yiyuclub.springbootrabbitmq.utils;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public  static String QUEUE_NAME = "queue_one";

    public  static String EXCHANGE_NAME = "exchange_one";

    public  static String ROUTING_NAME = "routing_one";

    @Bean
    public DirectExchange TestDirectExchange(){
        return new DirectExchange(EXCHANGE_NAME, true,false);
    }

    @Bean
    public Queue TestDirectQueue(){
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    public Binding TestBinding(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with(ROUTING_NAME);
    }

}
