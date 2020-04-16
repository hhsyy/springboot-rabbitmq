package com.yiyuclub.springbootrabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ConsumeController {

    @RabbitListener(queues = "queue_one")
    public void send(HashMap hm){
        System.out.println("接收中");
        System.out.println(hm.get("username"));
    }

}
