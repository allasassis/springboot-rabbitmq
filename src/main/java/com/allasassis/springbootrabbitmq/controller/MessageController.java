package com.allasassis.springbootrabbitmq.controller;

import com.allasassis.springbootrabbitmq.dto.User;
import com.allasassis.springbootrabbitmq.publisher.RabbitMQJsonProducer;
import com.allasassis.springbootrabbitmq.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitMQProducer producer;

    private final RabbitMQJsonProducer jsonProducer;

    public MessageController(RabbitMQJsonProducer jsonProducer, RabbitMQProducer producer) {
        this.jsonProducer = jsonProducer;
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        return ResponseEntity.status(201).body(producer.sendMessage(message));
    }

    @PostMapping("/publish/json")
    public ResponseEntity sendJson(@RequestBody User user) {
        jsonProducer.sendMessage(user);
        return ResponseEntity.status(201).build();
    }
}
