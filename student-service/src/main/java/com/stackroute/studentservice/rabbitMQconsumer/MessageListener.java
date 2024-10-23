package com.stackroute.studentservice.rabbitMQconsumer;

import com.stackroute.studentservice.entity.StudentDetails;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "MQConfig.QUEUE")
    public void listener(StudentDetails message) {
        System.out.println(message);
    }

}
