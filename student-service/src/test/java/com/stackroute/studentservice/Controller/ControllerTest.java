package com.stackroute.studentservice.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.studentservice.controller.StudentController;
import com.stackroute.studentservice.entity.StudentDetails;
import com.stackroute.studentservice.repository.StudentRepo;
import com.stackroute.studentservice.service.StudentService;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private StudentRepo studentRepo;
    @InjectMocks
    private StudentController studentController;

    StudentDetails RECORD_1 = new StudentDetails(1,"vikash","pawar","vikash@gmail.com",1234567890,
            22,"male","ECE","java","ambedkar nagar");

    StudentDetails RECORD_2 = new StudentDetails(2,"Suraj","singh","suraj@gmail.com",1122334455,
            23,"male","ECE","python","ambedkar nagar");

    StudentDetails RECORD_3 = new StudentDetails( 3,"Sagar","kalbhor","sagar@gmail.com",1234567890,
            24,"male","ECE","AWS","ambedkar nagar");


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void getAllRecord_success() throws Exception{
        List<StudentDetails> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));

           Mockito.when(studentRepo.findAll()).thenReturn(records);
         mockMvc.perform(MockMvcRequestBuilders
                   .get("/studentdetails")
                  .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
          .andExpect((ResultMatcher) jsonPath("$[2].firstName",is("Vikash")))
                 .andExpect((ResultMatcher) jsonPath("$[1].firstName",is("Suraj")));
    }

    @Test
    public void getStudentById_success() throws Exception{
        Mockito.when(studentRepo.findById((int) RECORD_1.getId()))
                .thenReturn(java.util.Optional.of(RECORD_1));

           mockMvc.perform(MockMvcRequestBuilders
                  .get("/findById/1")
                  .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
        .andExpect((ResultMatcher) jsonPath("$",notNullValue()))
         .andExpect((ResultMatcher) jsonPath("$.firstName",is("Sagar")));
    }

    @Test
    public void createRecord_success() throws Exception{
        StudentDetails record = StudentDetails.builder()
                .Id(40)
                .fristName("FirstName Test")
                .lastname("LastName Test")
                .emailId("java@gmail.com")
                .mobile(1234567890)
                .age(20)
                .gender("male")
                .course("b.tech")
                .subject("java")
                .address("Address text")
                .build();
          Mockito.when(studentRepo.save(record)).thenReturn(record);

          String contant = objectWriter.writeValueAsString(record);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/studentDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(contant);

         mockMvc.perform(mockRequest)
                 .andExpect(status().isOk())
         .andExpect((ResultMatcher) jsonPath("$",notNullValue()))
         .andExpect((ResultMatcher) jsonPath("$.firstName",is("Praveen")));
    }

    @Test
    public void updateStudentRecord_success() throws Exception{

        StudentDetails updatedRecord = StudentDetails.builder()
                .Id(1)
                .fristName("Update fristname")
                .lastname("update lastname")
                .emailId("update Emailid")
                .mobile(10)
                .age(100)
                .gender("update gender")
                .course("update course")
                .subject("update subject")
                .address("update adress")
                .build();

         Mockito.when(studentRepo.findById((int) RECORD_1.getId()))
             .thenReturn(java.util.Optional.of(RECORD_1));

         Mockito.when(studentRepo.save(updatedRecord))
               .thenReturn(updatedRecord);

        String updatedContent = objectWriter.writeValueAsString(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/studentDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

         mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
          .andExpect((ResultMatcher) jsonPath("$",notNullValue()))
         .andExpect((ResultMatcher) jsonPath("$.firstName", is("Update fristname")));

    }

    @Test
    public void deleteById() throws Exception{
Mockito.when(studentRepo.findById((int) RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/studentDetails/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

