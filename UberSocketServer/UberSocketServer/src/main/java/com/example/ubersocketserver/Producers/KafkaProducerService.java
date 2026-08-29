package com.example.ubersocketserver.Producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate; //key-value pair

    public KafkaProducerService( KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }

    public void publishMessage(String topic, String message){  //topic is key, message is the value
        kafkaTemplate.send(topic, message);
    }
}
