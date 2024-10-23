package com.stackroute.enrollmentservice.rabbitMQ;

import com.stackroute.enrollmentservice.model.Enrollment;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessageToRabbitMq(Enrollment enrollment) {

        EnrollmentToEmailDTO enrollmentToEmailDTO=new EnrollmentToEmailDTO();

        enrollmentToEmailDTO.setEnrollmentId(enrollment.getEnrollmentId());
        enrollmentToEmailDTO.setStudentId(enrollment.getStudentId());
        enrollmentToEmailDTO.setStudentName(enrollment.getStudentName());
        enrollmentToEmailDTO.setSessionName(enrollment.getSessionTitle());
        enrollmentToEmailDTO.setTutorName(enrollment.getTeacherName());

        rabbitTemplate.convertAndSend(MessageConfiguration.EXCHANGE, MessageConfiguration.ROUTING_KEY, enrollmentToEmailDTO);
    }
}