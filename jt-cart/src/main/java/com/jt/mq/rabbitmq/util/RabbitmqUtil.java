package com.jt.mq.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtil {

    // 提供私有的构造方法
    private RabbitmqUtil(){}

    /**
     * 获取rabbitmq信道
     * @param connection
     * @param channel
     * @param vhost
     * @return
     */
    public static Channel getChannel(Connection connection, Channel channel, String vhost){
       try {
           // 创建 ConnectionFactory，获取连接 connection
           ConnectionFactory connectionFactory = new ConnectionFactory();
           connectionFactory.setVirtualHost(vhost);
           connectionFactory.setHost("192.168.189.130");
           connectionFactory.setPort(5672);// 15672端口是管理界面的端口，5672才是后台端口
           connectionFactory.setUsername("admin");
           connectionFactory.setPassword("admin");
           connection = connectionFactory.newConnection();

           // 通过连接创建信道
           channel = connection.createChannel();
       } catch (Exception e){
           e.printStackTrace();
       }
        return channel;
    }

    /**
     * 关闭系统资源
     * @param connection
     * @param channel
     */
    public static void close(Connection connection, Channel channel){
        try {
            if (null != channel)
                channel.close();
            if (null != connection)
                connection.close();
        } catch (Exception e){
            System.out.println("rabbitq 系统资源关闭异常");
            e.printStackTrace();
        }
    }

}
