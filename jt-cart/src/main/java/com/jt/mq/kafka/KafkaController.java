package com.jt.mq.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * kafka对外接口
 *
 * @author zhaohualuo
 * @date 2019/12/17
 **/
@RestController
public class KafkaController {

    @Autowired
    KafkaProducer kafkaProducer;

    /**
     * 生产消息
     * @param str
     * @return
     */
    @RequestMapping(value = "/sendKafkaWithTestTopic",method = RequestMethod.GET)
    @ResponseBody
    public boolean sendTopic(@RequestParam String str){
        kafkaProducer.send(str);
        return true;
    }

    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaProducer.sendMessage1( normalMessage );
    }


    @GetMapping("/kafka/callbackOne/{message}")
    public void sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaProducer.sendMessage2( callbackMessage );
    }

    @GetMapping("/kafka/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaProducer.sendMessage3( callbackMessage );
    }


    @GetMapping("/kafka/transaction/{message}")
    public void sendMessage7(@PathVariable("message") String transactionMessage) {
        kafkaProducer.sendMessage7( transactionMessage );
    }

}
