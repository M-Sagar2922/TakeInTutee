package com.stackroute.authenticationservice.config.RabbitMqDomain;

import com.stackroute.authenticationservice.config.MessageConfig;
import com.stackroute.authenticationservice.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = MessageConfig.Queue)
    public void consumeMessageFromQueue1(User user) {
        System.out.println("Message recieved from user : " + user);
    }
    @RabbitListener(queues = MessageConfig.Queue2)
    public void consumeMessageFromQueue2(User user) {
        System.out.println("Message recieved from user : " + user);
    }
}