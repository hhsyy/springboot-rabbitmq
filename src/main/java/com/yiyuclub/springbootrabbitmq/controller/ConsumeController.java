package com.yiyuclub.springbootrabbitmq.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ConsumeController {

//    @RabbitListener(queues = "queue_one")
//    public void directRev(HashMap hm){
//        System.out.println("接收中");
//        System.out.println(hm.get("username"));
//    }
//
//    //接受topic.one的队列
//    @RabbitListener(queues = "topic.one")
//    public void topicRev1(HashMap hm){
//        System.out.println("topic1接收中:"+hm.get("username"));
//    }
//
//    //接受topic.two的队列
//    @RabbitListener(queues = "topic.two")
//    public void topicRev2(HashMap hm){
//        System.out.println("topic2接收中:"+hm.get("username"));
//    }
//
//
//    //接受topic.two的队列
//    @RabbitListener(queues = "fanout.one")
//    public void fanoutRev1(HashMap hm){
//        System.out.println("fanout1接收中:"+hm.get("username"));
//    }
//
//    //接受topic.two的队列
//    @RabbitListener(queues = "fanout.two")
//    public void fanoutRev2(HashMap hm){
//        System.out.println("fanout2接收中:"+hm.get("username"));
//    }
//
//    //接受topic.two的队列
//    @RabbitListener(queues = "fanout.three")
//    public void fanoutRev3(HashMap hm){
//        System.out.println("fanout3接收中:"+hm.get("username"));
//    }
//
//    //测试
//    @RabbitListener(queues = "test_ackqueue")
//    public void testRev(HashMap hm){
//        System.out.println("test接收中:"+hm.get("username"));
//    }


    //手动消费确认
    @RabbitListener(queues = "test_ackqueue")
    public void onMessage1(Message messages, Channel channel) throws IOException {
        try {
            System.out.println("消费确认1......：" + messages.getMessageProperties().getDeliveryTag());
            Thread.sleep(3000);
            String m = new String(messages.getBody());
            //参数1：DeliveryTag代表了RabbitMQ向该Channel投递的这条消息的唯一标识ID
            //参数2，为true时可以一次性确认小于等于delivery_tag的所有消息
            channel.basicAck(messages.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // deliveryTag对应的消息，第二个参数是否应用于多消息，第三个参数是否requeue
            // 第二个参数是否应用于多消息(与reject的区别)
            // 第三个参数是否requeue(重入队列)
            System.out.println("接收异常：" + e.getStackTrace());
            channel.basicNack(messages.getMessageProperties().getDeliveryTag(), false, true);

        }
    }

    //手动消费确认
    @RabbitListener(queues = "test_ackqueue")
    public void onMessage2(Message messages, Channel channel) throws IOException {
        try {
            System.out.println("消费确认2......：" + messages.getMessageProperties().getDeliveryTag());
            Thread.sleep(3000);
            String m = new String(messages.getBody());
            //参数1：DeliveryTag代表了RabbitMQ向该Channel投递的这条消息的唯一标识ID
            //参数2，为true时可以一次性确认小于等于delivery_tag的所有消息
            channel.basicAck(messages.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // deliveryTag对应的消息，第二个参数是否应用于多消息，第三个参数是否requeue
            // 第二个参数是否应用于多消息(与reject的区别)
            // 第三个参数是否requeue(重入队列)
            System.out.println("接收异常：" + e.getStackTrace());
            channel.basicNack(messages.getMessageProperties().getDeliveryTag(), false, true);

        }
    }
}
