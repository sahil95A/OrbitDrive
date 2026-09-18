package com.example.ubersocketserver.controller;

import com.example.ubersocketserver.Producers.KafkaProducerService;
import com.example.ubersocketserver.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/socket")
public class DriverRequestController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RestTemplate restTemplate;
    private final KafkaProducerService kafkaProducerService;

    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate, KafkaProducerService kafkaProducerService1) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.kafkaProducerService = kafkaProducerService1;
        this.restTemplate = new RestTemplate();

    }

    @GetMapping
    public Boolean help() {
        kafkaProducerService.publishMessage("sample-topic", "Hello");
        return true;
    }


    @PostMapping("/newride")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto requestDto) {
        System.out.println("request for rides recieved");
        sendDriversNewRideRequest(requestDto);
        System.out.println("req completed");
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }


    public void sendDriversNewRideRequest(RideRequestDto requestDto) {
        System.out.println("Executed periodic function");
        simpMessagingTemplate.convertAndSend("/topic/rideRequest", requestDto);
    }

    @MessageMapping("/rideResponse/{userId}")
    public void rideResponseHandler(@DestinationVariable String userId, RideResponseDto rideResponseDto) {

        System.out.println(rideResponseDto.getResponse() + " " + userId);
        UpdateBookingRequestDto requestDto = UpdateBookingRequestDto.builder()
                .driverId(Optional.of(Long.parseLong(userId)))
                .status("SCHEDULED")
                .build();
        ResponseEntity<UpdateBookingResponseDto> result =this.restTemplate.postForEntity("http://localhost:7477/api/v1/booking/" + rideResponseDto.bookingId, requestDto, UpdateBookingResponseDto.class);
        kafkaProducerService.publishMessage("sample-topic", "Hello");

        System.out.println(result.getStatusCode());
    }

    }

