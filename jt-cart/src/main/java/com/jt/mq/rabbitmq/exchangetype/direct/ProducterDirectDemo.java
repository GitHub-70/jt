package com.jt.mq.rabbitmq.exchangetype.direct;

import com.jt.mq.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.UUID;

public class ProducterDirectDemo {

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

           // 声明默认的队列
           channel.queueDeclare(queneName, true, false, true, null);

           while (true) {
               int i = 0;
               // 每秒发送一个随机 uuid 消息
               channel.basicPublish("", queneName, null, UUID.randomUUID().toString().getBytes());
               i++;
               if (i == 100){
                   break;
               }
               Thread.sleep(1000);
           }
       } catch (Exception e){
           e.printStackTrace();

       } finally {
           // 关闭系统资源
           RabbitmqUtil.close(connection, channel);
       }

    }
}
