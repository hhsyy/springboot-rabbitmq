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
    public void directRev(HashMap hm){
        System.out.println("接收中");
        System.out.println(hm.get("username"));
    }

    //接受topic.one的队列
    @RabbitListener(queues = "topic.one")
    public void topicRev1(HashMap hm){
        System.out.println("topic1接收中:"+hm.get("username"));
    }

    //接受topic.two的队列
    @RabbitListener(queues = "topic.two")
    public void topicRev2(HashMap hm){
        System.out.println("topic2接收中:"+hm.get("username"));
    }


    //接受topic.two的队列
    @RabbitListener(queues = "fanout.one")
    public void fanoutRev1(HashMap hm){
        System.out.println("fanout1接收中:"+hm.get("username"));
    }

    //接受topic.two的队列
    @RabbitListener(queues = "fanout.two")
    public void fanoutRev2(HashMap hm){
        System.out.println("fanout2接收中:"+hm.get("username"));
    }

    //接受topic.two的队列
    @RabbitListener(queues = "fanout.three")
    public void fanoutRev3(HashMap hm){
        System.out.println("fanout3接收中:"+hm.get("username"));
    }

}
