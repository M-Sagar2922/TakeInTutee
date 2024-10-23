package com.stackroute.enrollmentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.enrollmentservice.model.Enrollment;
import com.stackroute.enrollmentservice.service.EnrollmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class EnrollmentControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EnrollmentServiceImpl enrollServiceImpl;;

    @InjectMocks
    private EnrollmentController enrollmentController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(enrollmentController).build();
    }



    @Test
    @DisplayName("Delete /disEnrollment Test - Success")
    public void sessionDisEnroll_success() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders
                    .delete("/disEnrollment/201")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

        @Test
    @DisplayName("Post /getEnrollmentId Test - Success")
    public void getEnrollId_success() throws Exception{

            Enrollment enrollmentDetails = Enrollment.builder()
                    .enrollmentId(50)
                    .enrollmentDesc("Enrollment for Java session")
                    .studentId(103)
                    .studentName("sagar")
                    .sessionId(203)
                    .sessionTitle("Java")
                    .teacherId(303)
                    .teacherName("ravi")
                    .build();

            Mockito.when(enrollServiceImpl.getEnrollId(enrollmentDetails.getStudentId(),enrollmentDetails.getSessionId()))
                    .thenReturn(Optional.of(enrollmentDetails.getEnrollmentId()));
            String content = objectWriter.writeValueAsString(enrollmentDetails);

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                    .post("/getEnrollmentId/103/203")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content);

            mockMvc.perform(MockMvcRequestBuilders
                    .post("/getEnrollmentId/103/203")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

    }

}
