package com.jt.mq.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;


@Component
public class KafkaCustomConfig {


    @Autowired
    ConsumerFactory consumerFactory;

    /**
     * 消息过滤器可以在消息抵达consumer之前被拦截，在实际应用中，
     * 我们可以根据自己的业务逻辑，筛选出需要的信息再交由KafkaListener处理，
     * 不需要的消息则过滤掉。
     *
     * 配置消息过滤只需要为 监听器工厂 配置一个RecordFilterStrategy（消息过滤策略），
     * 返回true的时候消息将会被抛弃，返回false时，消息能正常抵达监听容器。
     * bean的名称为方法名
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }

    /**
     * 通过异常处理器，我们可以处理consumer在消费时发生的异常。
     * bean的名称为方法名
     */
    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("消费异常："+message.getPayload());
            return null;
        };
    }



}
