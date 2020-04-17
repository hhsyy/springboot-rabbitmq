package com.yiyuclub.springbootrabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ProductController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("send")
    public String send(String username){
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("username",username);

//      direct方式
//      rabbitTemplate.convertAndSend("exchange_one","routing_one",hm);

//      topic方式
//      1:routingKey为topic.one或topic.two可发送到队列(topic.one被发送到topic.one和topic.#队列中，topic.two只能发送到topic.#队列中)
//      2：routingKey 为test.one不能发送
//        rabbitTemplate.convertAndSend("topic_exchange","topic.one",hm);
//        rabbitTemplate.convertAndSend("topic_exchange","topic.two",hm);

//      fanout方式 无需制定routingkey

        rabbitTemplate.convertAndSend("fanout_exchange",null,hm);
        return "success!";
    }
}
