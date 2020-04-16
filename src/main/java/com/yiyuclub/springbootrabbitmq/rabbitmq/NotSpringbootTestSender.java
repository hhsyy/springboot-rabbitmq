package com.yiyuclub.springbootrabbitmq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import java.io.IOException;

public class NotSpringbootTestSender {

    //获得rabbitmq链接
    public static Connection getRabbitmqConnection() throws IOException {
        ConnectionFactory cf = new ConnectionFactory();

        //测试时局域网ip失败，ipv4成功，localhost成功
        cf.setHost("192.168.149.1");
        cf.setPort(5672);
        cf.setUsername("root");
        cf.setPassword("root");
        cf.setVirtualHost("testrabbitmq");


        return  cf.newConnection();
    }

    public static void main(String[] args) throws IOException {
        //得到连接对象
        Connection connection = getRabbitmqConnection();
        //获取信道
        Channel channel = connection.createChannel();
        //创建队列(队列名称，是否持久化，是否私有排外，断开连接后是否自动删除,队列消息删除策略)
        channel.queueDeclare("test",false,false,false,null);
        //推送消息
        // 1.(路由名称，路由关键字，消息属性，消息体)
        // 2.(路由名称，路由关键字，是否为强制性,消息属性，消息体)
        // 3.(路由名称，路由关键字，是否为强制性，是否立即发送，消息属性，消息体)
        //默认的交换机，routingKey等于队列名称
        channel.basicPublish("","test",false,null,"helloworld".getBytes());
        System.out.println("已发送消息:helloworld");
        //关闭信道
        channel.close();
        //关闭连接
        connection.close();

    }


}
