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

        rabbitTemplate.convertAndSend("exchange_one","routing_one",hm);
        return "success!";
    }
}
