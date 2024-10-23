package com.stackroute.emailservice.service;

import com.stackroute.emailservice.modal.EmailServiceModal;
import com.stackroute.emailservice.rabbitmq.StudentDTO;
import com.stackroute.emailservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendEmail(EmailServiceModal entityBean) throws Exception {
        List<StudentDTO> studentList = repository.findByStudentEmail(entityBean.getRecipient());
        if (studentList.size() > 0) {
            StudentDTO student = studentList.get(0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(entityBean.getMessage());
            stringBuilder.append("\n Enrollment Id : " + student.getEnrollmentId());
            stringBuilder.append("\n Tutor Name : " + student.getTutorName());
            stringBuilder.append("\n Session Name : " + student.getSessionName());
            stringBuilder.append("\n Session Fee : " + student.getSessionFee());
            entityBean.setMessage(stringBuilder.toString());
        }

        if (entityBean.getAttachment() == null || entityBean.getAttachment().isEmpty()) {
            return sendSimpleMail(entityBean);
        } else {
            return sendMailWithAttachment(entityBean);
        }


    }

    @Override
    public StudentDTO saveStdDetail(StudentDTO student) throws Exception {
        return repository.save(student);
    }

    public String sendSimpleMail(EmailServiceModal entityBean) {

        // Creating a simple mail message
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();
        // Setting up necessary details
        mailMessage.setFrom(sender);
        mailMessage.setTo(entityBean.getRecipient());
        mailMessage.setText(entityBean.getMessage());
        mailMessage.setSubject(entityBean.getSubject());

        // Sending the mail
        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully...";
    }

    // Method 2
    // To send an email with attachment
    public String sendMailWithAttachment(EmailServiceModal entityBean) throws Exception {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        // Setting multipart as true for attachments to be sent
        mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(entityBean.getRecipient());
        mimeMessageHelper.setText(entityBean.getMessage());
        mimeMessageHelper.setSubject(entityBean.getSubject());

        // Adding the attachment
        FileSystemResource file = new FileSystemResource(new File(entityBean.getAttachment().toString()));
        mimeMessageHelper.addAttachment(file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);
        return "Mail sent Successfully";


    }
}

