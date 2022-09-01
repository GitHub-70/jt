package com.jt.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * kafka生产者工具类
 *
 * @author zhaohualuo
 * @date 2019/12/17
 **/
@Component
public class KafkaProducer {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 生产数据
     * @param str 具体数据
     */
    public void send(String str) {
        System.out.println("生产数据：" + str);
        kafkaTemplate.send("testTopic", str);
    }


     // 发送消息
    public void sendMessage1(String normalMessage) {
        System.out.println("生产数据：" + normalMessage);
         kafkaTemplate.send("topic1", normalMessage);
    }


    /**
     * 生产者基于回调的方法--方式一
     * @param callbackMessage
     */
    public void sendMessage2(String callbackMessage) {
        kafkaTemplate.send("topic2", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            logger.info("发送消息失败:" + failure.getMessage());
        });
    }


    /**
     * 生产者基于回调的方法--方式二
     * @param callbackMessage
     */
    public void sendMessage3(String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(
                new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("发送消息失败："+ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }

    /**
     * kafka事务提交
     */
    public void sendMessage7(String transactionMessage){
        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send("topic1","test1" + transactionMessage);
            throw new RuntimeException("fail");
        });

        // 不声明事务：后面报错但前面消息已经发送成功了
        kafkaTemplate.send("topic1","test2" + transactionMessage);
        throw new RuntimeException("fail");
    }


}

