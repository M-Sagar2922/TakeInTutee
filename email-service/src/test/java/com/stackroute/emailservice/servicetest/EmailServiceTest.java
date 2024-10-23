package com.stackroute.emailservice.servicetest;

import com.stackroute.emailservice.modal.EmailServiceModal;
import com.stackroute.emailservice.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("Test")
public class EmailServiceTest {
    @Autowired
    private EmailService service;



    @Test
    @DisplayName("Test 1-Sending Email without Attachment")
    public void emailService_test1() throws Exception {
        EmailServiceModal input1=new EmailServiceModal("chetandhoble11@gmail.com",
                "sending mail for testing API","API TESTING",null);
       assertEquals("Mail Sent Successfully...",service.sendEmail(input1));

    }
    @Test
    @DisplayName("Test 2-Sending Email with Attachment")
    public void emailService_test2() throws Exception {
        byte[] content = null;
        File file = new File("/home/globallogic/Desktop/BD_122111_100.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain",content);
        EmailServiceModal input1=new EmailServiceModal("chetandhoble11@gmail.com",
                "sending mail for testing API","API TESTING",multipartFile);
        assertEquals("Mail Sent Successfully...",service.sendEmail(input1));

    }

}
