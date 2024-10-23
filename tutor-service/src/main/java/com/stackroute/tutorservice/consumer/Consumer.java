package com.stackroute.tutorservice.consumer;

import com.stackroute.tutorservice.config.MessageConfig;
import com.stackroute.tutorservice.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
//    @RabbitListener(queues = MessageConfig.Queue)
//    public void consumeMessageFromQueue1(UserDetails userDetails) {
//        System.out.println("Message recieved from userDetails : " + userDetails);
//    }
    @RabbitListener(queues = MessageConfig.Queue)
public void consumeMessageFromQueue(User user){
    System.out.println("message recevied from user" +user);
      ;
}
}
