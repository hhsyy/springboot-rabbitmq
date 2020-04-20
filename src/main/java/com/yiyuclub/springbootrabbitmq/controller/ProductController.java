package com.yiyuclub.springbootrabbitmq.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ProductController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //  发送测试
    @GetMapping("send")
    public String send(String username) {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("username", username);

//      direct方式
        rabbitTemplate.convertAndSend("exchange_one", "routing_one", hm);

//      topic方式
//      1:routingKey为topic.one或topic.two可发送到队列(topic.one被发送到topic.one和topic.#队列中，topic.two只能发送到topic.#队列中)
//      2：routingKey 为test.one不能发送
//      rabbitTemplate.convertAndSend("topic_exchange","topic.one",hm);
//      rabbitTemplate.convertAndSend("topic_exchange","topic.two",hm);

//      fanout方式 无需制定routingkey
//      rabbitTemplate.convertAndSend("fanout_exchange",null,hm);
        return "success!";
    }

    //  验证消息返回值测试
    @GetMapping("acksend")
    public String ackSend(String username) {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("username", username);
        //将CorrelationData放入可在confirm中不为空，并绑定id
        CorrelationData c = new CorrelationData(IdUtil.simpleUUID());

        //情况1 交换机 testexchange不存在, 队列存在，输出ConfirmCallback函数的内容
        //rabbitTemplate.convertAndSend("testexchange","test_ack",hm);

        //情况2 交换机 test_ackexchange存在，队列不存在 输出ConfirmCallback（true）和ReturnCallback函数的内容
        //rabbitTemplate.convertAndSend("test_ackexchange","test_key",hm);

        //情况3 交换机 testexchange不存在，队列不存在 与1同
        //rabbitTemplate.convertAndSend("testexchange","test_key",hm);

        //情况4 交换机 test_ackexchange存在，队列存在 只显示ConfirmCallback回调函数内容（true）
        //rabbitTemplate.convertAndSend("test_ackexchange","test_ack",hm);

        //消费者确认测试
        rabbitTemplate.convertAndSend("test_ackexchange", "test_ack", hm, c);
        return "success!";
    }
}
