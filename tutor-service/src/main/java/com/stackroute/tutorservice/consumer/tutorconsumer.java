package com.stackroute.tutorservice.consumer;

//import org.springframework.amqp.rabbit.annotation.RabbitListener;


//
//import com.stackroute.tutorservice.config.MessagingConfig;
//import com.stackroute.tutorservice.model.TeachersDetails;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
//import org.springframework.stereotype.Component;
//
//@Component
//public class tutorconsumer {
//
//    @RabbitListener(queues = MessagingConfig.QUEUE_SESSION)
//    public void consumeMessageFromQueue(Integer teachersId) {
//        System.out.println("Message recieved from queue teachersId : " + teachersId);
//    }
////@RabbitListener(queues = MessageConfig)
////    public void consumerMessageFromAuthQueue(String username){
////        System.out.println("message recieved form auth queue !!"+username);
////    }
//    @RabbitListener(queues = MessagingConfig.QUEUE)
//    public void consumeMessageFromQueue1(String teachersId) {
//        System.out.println("Message recieved from queue Email : " + teachersId);
//    }
//}
