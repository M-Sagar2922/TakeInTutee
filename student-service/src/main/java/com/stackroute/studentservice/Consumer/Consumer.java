package com.stackroute.studentservice.Consumer;

import com.stackroute.studentservice.config.MessageConfig;
import com.stackroute.studentservice.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
//    @RabbitListener(queues = MessageConfig.Queue)
//    public void consumeMessageFromQueue1(UserDetails userDetails) {
//        System.out.println("Message recieved from userDetails : " + userDetails);
//    }
    @RabbitListener(queues = MessageConfig.Queue2)
public void consumeMessageFromQueue(User user){
    System.out.println("message recevied from user" +user);

}
}
