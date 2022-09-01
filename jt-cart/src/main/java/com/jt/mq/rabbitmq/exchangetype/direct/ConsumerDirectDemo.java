package com.jt.mq.rabbitmq.exchangetype.direct;

import com.jt.mq.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerDirectDemo {

    public static void main(String[] args) {
        
        Connection connection = null;
        Channel channel = null;
        String queneName = "testQuene";
//        String vhost = "rabbitmq-vhost-虚拟主机";

        // 该rabbitmq虚拟主机，可登陆到15672管理界面查看
        String vhost = "/";

       try {
           // 获取通道
           channel = RabbitmqUtil.getChannel(connection, channel, vhost);

           Consumer consumer = new DefaultConsumer(channel){
               @Override
               public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                          byte[] body) throws IOException {
                   String message = new String(body, "UTF-8");
                   System.out.println(envelope.getExchange() + "," + envelope.getRoutingKey() + "," + message);
               }
           };

           // channel绑定队列，autoAck为true表示一旦收到消息则自动回复确认消息
           channel.basicConsume(queneName, true, consumer);

       } catch (Exception e){
           e.printStackTrace();
           
       } finally {
           // 关闭系统资源
           RabbitmqUtil.close(connection, channel);
       }

    }
}
