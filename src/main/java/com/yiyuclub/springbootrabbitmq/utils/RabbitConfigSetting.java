package com.yiyuclub.springbootrabbitmq.utils;


import org.apache.coyote.OutputBuffer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitConfigSetting implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
        //指定 ReturnCallback
        rabbitTemplate.setReturnCallback(this);
    }
    // CorrelationData correlationData 相应数据
    // boolean ack  响应答案
    // String cause 原因
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            System.out.println("消息："+correlationData);
        }else{
            System.out.println("错误："+s);
        }
    }


    // Message message 消息s
    // int replyCode 状态
    // String replyText 描述
    // String exchange 交换机
    // String routingKey 路由键
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        // 反序列化对象输出
        System.out.println("消息主体: " + SerializationUtils.deserialize(message.getBody()));
        System.out.println("状态: " + i);
        System.out.println("描述：" + s);
        System.out.println("交换器 : " + s1);
        System.out.println("路由键 : " + s2);
    }
}
