package com.example.ubersocketserver.Consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService1 {

    @KafkaListener(topics= "sample-topic", groupId = "sample-group-3") //which topics will this listner will listen
    public void listen(String message){ //any mssg will come to this sample topic, will listen here
        System.out.println("kafka consumer new message from topic sample topic " + message);

    }
    

}
