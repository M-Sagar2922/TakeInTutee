package com.stackroute.sessionservice.rabbitmq;

import com.stackroute.sessionservice.exceptions.SessionAlreadyExistsException;
import com.stackroute.sessionservice.model.Session;
import com.stackroute.sessionservice.service.SessionServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private SessionServiceImpl sessionService;


    @RabbitListener(queues = "session_queue")
    public void getUserDtoFromRabbitMq(Session session) throws SessionAlreadyExistsException {
        System.out.println("session details are" + session.toString());
        Session session1 = new Session();
        session1.setTeacherId(session.getTeacherId());
        sessionService.createSession(session1);
    }


}
