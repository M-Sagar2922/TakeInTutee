package com.stackroute.emailservice.rabbitmq;


import com.stackroute.emailservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @Autowired
    EmailService service;

    @RabbitListener(queues = {"EnrollmentToEmailService"})
    public void consume(StudentDTO message) throws Exception {
        System.out.println("rabbit mq msg........" + message.toString());
        service.saveStdDetail(message);
    }
}
