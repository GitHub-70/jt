package com.jt.mq.rabbitmq.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * rabbitmq 消费者监听类
 *          监听的队列：fanout.B
 */
//@Component
@RabbitListener(queues = "fanout.B")//监听的队列名称 fanout.B
public class FanoutReceiverB {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverB消费者收到消息  : " +testMessage.toString());
    }

}