package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.exception.EmailServiceException;
import com.stackroute.emailservice.modal.EmailServiceDTO;
import com.stackroute.emailservice.modal.EmailServiceModal;
import com.stackroute.emailservice.rabbitmq.MQConfig;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/email-service")
public class EmailServiceController {
    @Autowired
    EmailService service;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/notificationsEmail")
    public ResponseEntity<Map> sendEmail(@Valid @RequestBody EmailServiceDTO entityBean) throws EmailServiceException {
        EmailServiceModal entity = new EmailServiceModal(
                entityBean.getRecipient(), entityBean.getMessage(), entityBean.getSubject(), entityBean.getAttachment()
        );
        String displayMsg = "";
        Map resultMap = new HashMap();
        try {
            displayMsg = service.sendEmail(entity);
            resultMap.put("displayMsg", displayMsg);
            resultMap.put("isSuccess", true);
            return new ResponseEntity<Map>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailServiceException("Error while Sending Mail");
        }
    }
}
