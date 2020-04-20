package com.yiyuclub.springbootrabbitmq.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

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

        return cf.newConnection();
    }

    public static void main(String[] args) throws Exception {
        //得到连接对象
        Connection connection = getRabbitmqConnection();
        //获取信道
        Channel channel = connection.createChannel();
        //限流，每个消费者2个 true为channel级别，FALSE为消费者级别
        channel.basicQos(0,2,true);
        //获取消费者,输入监听内容
        DefaultConsumer qc = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列 队列名称 是否自动ack 重写消费者方法
        channel.basicConsume("queue_not", true, qc);


    }

}
