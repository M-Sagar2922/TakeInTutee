package com.stackroute.studentservice.rabbitMQconsumer;


import com.stackroute.emailservice.rabbitmq.MQConfig;
import com.stackroute.studentservice.entity.StudentDetails;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/producer")
    public String producerMessage(@RequestBody StudentDetails message) {
        message.getId();
        message.setFristName(String.valueOf(new StudentDetails()));
        message.getLastname();
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, message);

        return "Message producer";
    }
}
