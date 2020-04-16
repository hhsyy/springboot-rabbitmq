package com.yiyuclub.springbootrabbitmq.rabbitmq;

import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.listener.BlockingQueueConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NotSpringbootTestRev {
    //获得rabbitmq链接
    public static Connection getRabbitmqConnection() throws Exception {
        ConnectionFactory cf = new ConnectionFactory();

        //测试时局域网ip失败，ipv4成功，localhost成功
        cf.setHost("192.168.149.1");
        cf.setPort(5672);
        cf.setUsername("root");
        cf.setPassword("root");
        cf.setVirtualHost("testrabbitmq");

        return  cf.newConnection();
    }

    public static void main(String[] args) throws Exception {
        //得到连接对象
        Connection connection = getRabbitmqConnection();
        //获取信道
        Channel channel = connection.createChannel();
        //创建队列(队列名称，是否持久化，是否私有排外，断开连接后是否自动删除,队列消息删除策略)
        channel.queueDeclare("test",false,false,false,null);
        //获取消费者
        DefaultConsumer qc = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        };
        //监听队列
        channel.basicConsume("test",true,qc);


    }

}
