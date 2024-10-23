package com.stackroute.emailservice.service;

import com.stackroute.emailservice.modal.EmailServiceModal;
import com.stackroute.emailservice.rabbitmq.StudentDTO;

public interface EmailService {
    public String sendEmail(EmailServiceModal entityBean) throws Exception;
    public StudentDTO saveStdDetail(StudentDTO student) throws Exception;

}
