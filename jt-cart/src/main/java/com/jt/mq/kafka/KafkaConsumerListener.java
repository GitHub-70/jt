package com.jt.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * kafka消费者监听消息
 *
 * @author zhaohualuo
 * @date 2019/12/17
 **/
@Component
public class KafkaConsumerListener {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = "testTopic")
    public void onMessage(String str){
        //insert(str);//这里为插入数据库代码
        logger.info("监听到：" + str);
        System.out.println("监听到：" + str);
    }


//    @KafkaListener(topics = {"topic1"})
//    public void onMessage1(ConsumerRecord<?, ?> record){
//        // 消费的哪个topic、partition的消息,打印出消息内容
//        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
//    }


    /**
     * @Title 指定topic、partition、offset消费
     * @Description 同时监听topic1和topic2，监听topic1的0号分区、
     *              topic2的 0号分区 和 1号分区的offset为8开始的消息
     * @Author long.yuan
     * @Date 2020/3/22 13:38
     * @Param [record]
     * @return void
     *
     * 注意：topics和topicPartitions不能同时使用；
     **/
    @KafkaListener(id = "consumer1",groupId = "felix-group",topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = { "0" }),
            @TopicPartition(topic = "topic2", partitions = "0",
                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
    })
    public void onMessage2(ConsumerRecord<?, ?> record) {
        System.out.println("topic:"+record.topic()+"|partition:"+record.partition()+"|offset:"+record.offset()+"|value:"+record.value());
    }


    /**
     * 接收消息用List
     * @param records
     */
    @KafkaListener(id = "consumer2",groupId = "felix-group", topics = "topic3")
    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
        System.out.println(">>>批量消费一次，records.size()="+records.size());
        for (ConsumerRecord<?, ?> record : records) {
            System.out.println(record.value());
        }
    }

    /**
     * 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
     * @param record
     * @throws Exception
     */
    @KafkaListener(topics = {"topic4"},errorHandler = "consumerAwareErrorHandler")
    public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
        throw new Exception("简单消费-模拟异常");
    }

    /**
     * 批量消费也一样，异常处理器的message.getPayload()也可以拿到各条消息的信息
     * @param records
     * @throws Exception
     */
    @KafkaListener(topics = "topic5",errorHandler="consumerAwareErrorHandler")
    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
        for (ConsumerRecord<?, ?> record : records) {
            System.out.println("批量消费一次：" + record.value());
            throw new Exception("批量消费-模拟异常");
        }
    }

    // 消息过滤监听
    @KafkaListener(topics = {"topic6"},containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());
    }


    /**
     * 在实际开发中，我们可能有这样的需求，应用A从TopicA获取到消息，
     * 经过处理后转发到TopicB，再由应用B监听处理消息，
     * 即一个应用处理完成后将该消息转发至其他应用，完成消息的转发。
     *
     * @Title 消息转发
     * @Description 从topic7接收到的消息经过处理后转发到topic2
     * @Author long.yuan
     * @Date 2020/3/23 22:15
     * @Param [record]
     * @return void
     **/
    @KafkaListener(topics = {"topic7"})
    @SendTo("topic2")
    public String onMessage7(ConsumerRecord<?, ?> record) {
        return record.value()+"-forward message";
    }

}
