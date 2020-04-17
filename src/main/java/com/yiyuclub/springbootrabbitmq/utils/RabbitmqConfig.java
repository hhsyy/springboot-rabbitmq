package com.yiyuclub.springbootrabbitmq.utils;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    //Direct方式
    public  static String QUEUE_NAME = "queue_one";
    public  static String EXCHANGE_NAME = "exchange_one";
    public  static String ROUTING_NAME = "routing_one";

    //topic方式
    public static String TOPIC_ONE = "topic.one";
    public static String TOPIC_TWO = "topic.two";
    public static String TOPIC_EXCHANGE = "topic_exchange";

    //fanout方式
    public static String FANOUT_ONE = "fanout.one";
    public static String FANOUT_TWO = "fanout.two";
    public static String FANOUT_THREE = "fanout.three";
    public static String FANOUT_EXCHANGE = "fanout_exchange";


//   Direct方法
    @Bean
    public DirectExchange TestDirectExchange(){
        return new DirectExchange(EXCHANGE_NAME, true,false);
    }

    @Bean
    public Queue TestDirectQueue(){
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    public Binding TestDirectBinding(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with(ROUTING_NAME);
    }

// //分割线-----------------------

//    topic方法
//    @Bean
//    public Queue topicQueue_one(){
//        return new Queue(TOPIC_ONE,true);
//    }
//
//    @Bean
//    public Queue topicQueue_two(){
//        return new Queue(TOPIC_TWO,true);
//    }
//
//    @Bean
//    public TopicExchange topicExchange(){
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//
//    @Bean
//    public Binding topicBind_one(){
//        return BindingBuilder.bind(topicQueue_one()).to(topicExchange()).with("topic.one");
//    }
//
//    @Bean
//    public Binding topicBind_two(){
//        return BindingBuilder.bind(topicQueue_two()).to(topicExchange()).with("topic.#");
//    }

//    //分割线-----------------------

//  fanout 无需指定routingkey，指定也不生效
//    @Bean
//    public Queue fanoutQueue_one(){
//        return new Queue(FANOUT_ONE,true);
//    }
//
//    @Bean
//    public Queue fanoutQueue_two(){
//        return new Queue(FANOUT_TWO,true);
//    }
//
//    @Bean
//    public Queue fanoutQueue_three(){
//        return new Queue(FANOUT_THREE,true);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//
//    @Bean
//    public Binding fanoutBingding_one(){
//        return BindingBuilder.bind(fanoutQueue_one()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding fanoutBingding_two(){
//        return BindingBuilder.bind(fanoutQueue_two()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding fanoutBingding_three(){
//        return BindingBuilder.bind(fanoutQueue_three()).to(fanoutExchange());
//    }

//    //分割线-----------------------
    //测试确认返回配置
    @Bean
    public DirectExchange testAckExchange(){
        return new DirectExchange("test_ackexchange",true,false);
    }

    @Bean
    public Queue testAckQueue(){
        return new Queue("test_ackqueue",true);
    }

    @Bean
    public Binding TestAckBinding(){
        return BindingBuilder.bind(testAckQueue()).to(testAckExchange()).with("test_ack");
    }
}
