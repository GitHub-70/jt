package com.jt.mq.rabbitmq.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * rabbitmq 消费者监听类
 *          监听的队列：topicExchange
 */
//@Component
@RabbitListener(queues = "topic.woman")//监听的队列名称 topicExchange
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("第一个TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }

}