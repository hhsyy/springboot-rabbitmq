package com.yiyuclub.springbootrabbitmq.utils;

import com.yiyuclub.springbootrabbitmq.controller.ConsumeController;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumeConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private ConsumeController consume;//Direct消息接收处理类

    @Autowired
    RabbitmqConfig config;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setQueues(config.TestDirectQueue());  //这里如果在这边设置了监听的队列 和下面设置了消费的监听类，那么对应的监听类可以去掉监听类的注解 ，谢谢有朋友指出这一点
        container.setMessageListener(consume);
//        container.addQueues(fanoutRabbitConfig.queueA());
//        container.setMessageListener(fanoutReceiverA);
        return container;
    }

}
