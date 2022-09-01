package com.jt.mq.rabbitmq.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * rabbitmq 消费者监听类
 *          监听的队列：fanout.C
 */
//@Component
@RabbitListener(queues = "fanout.C")//监听的队列名称 fanout.C
public class FanoutReceiverC {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverC消费者收到消息  : " +testMessage.toString());
    }

}