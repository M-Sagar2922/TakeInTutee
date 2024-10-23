package com.stackroute.emailservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.emailservice.modal.EmailServiceModal;
import com.stackroute.emailservice.service.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceControllerTest {
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailServiceController  emailServiceController;

    EmailServiceModal input1=new EmailServiceModal("chetandhoble11@gmail.com",
            "sending mail for testing API","API TESTING",null);

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(emailServiceController).build();
    }

    @Test
    @DisplayName("Test 1-Sending Email without Attachment")
    public void sendMail_success() throws Exception{
        EmailServiceModal input=EmailServiceModal.builder()
                .recipient("chetandhoble11@gmail.com")
                .message("mail for testing")
                .subject("testing mail")
                .build();
        String content = objectWriter.writeValueAsString(input);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/emailService/notificationsEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.isSuccess",is(true)));

    }


}
