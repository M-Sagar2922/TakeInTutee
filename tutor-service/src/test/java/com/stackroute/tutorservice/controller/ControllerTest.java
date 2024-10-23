package com.stackroute.tutorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.tutorservice.model.TeachersDetails;
import com.stackroute.tutorservice.service.TutorService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private TutorService tutorService;

    @InjectMocks
    private TutorController tutorController;

    TeachersDetails RECORD_1 = new TeachersDetails(1,"Fatha","JKD","Fatha@gmail.com"
            ,26,"Male","9876543212",5,"Java","Engineering");

    TeachersDetails RECORD_2 = new TeachersDetails(2,"Mehboob","Jamadar","mehboob@gmail.com"
            ,28,"Male","78957634524",7,"Big Data","B.E");

    TeachersDetails RECORD_3 = new TeachersDetails(3,"Yumin","Gu","yumin@gmail.com"
            ,32,"Female","2138623567",10,"AWS","B.Tech");


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tutorController).build();
    }

    @Test
    @DisplayName("Get /findAllTutors Test - Success ")
    public void getAllRecord_success() throws Exception{
        List<TeachersDetails> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));

        Mockito.when(tutorService.readTutorDetails()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/findAllTutors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].firstName",is("Fatha")))
                .andExpect(jsonPath("$[1].firstName",is("Mehboob")));
    }

    @Test
    @DisplayName("Get /findById Test - Success ")
    public void getTeacherById_success() throws Exception{
        Mockito.when(tutorService.findTeacherById(RECORD_1.getTeacherId()))
                .thenReturn(java.util.Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/findById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstName",is("Fatha")));
    }

    @Test
    @DisplayName("Post /addTeacher Test - Success")
    public void createRecord_success() throws Exception{
        TeachersDetails teachersDetails = TeachersDetails.builder()
                .teacherId(40)
                .firstName("FirstName Test")
                .lastName("LastName Test")
                .age(34)
                .subject("Test Cases")
                .gender("Female")
                .mobileNumber("9070650403")
                .qualification("Java Developer")
                .emailId("java@gmail.com")
                .build();

        Mockito.when(tutorService.addTutorDetails(teachersDetails)).thenReturn(teachersDetails);
        String content = objectWriter.writeValueAsString(teachersDetails);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/addTeacher")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstName",is("FirstName Test")));
    }

    @Test
    public void deleteByIda_success() throws Exception{
//        Mockito.when(tutorService.findTeacherById(RECORD_2.getTeacherId()))
//                .thenReturn(Optional.of(RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



//    @Test
//    public void updateTutorRecord_success() throws Exception{
//
//        TeachersDetails updateTeachersDetails = TeachersDetails.builder()
//                .teacherId(1)
//                .firstName("Update TestCase")
//                .age(36).build();
//
////        Mockito.when(tutorService.findTeacherById(RECORD_1.getTeacherId()))
////                .thenReturn(java.util.Optional.of(RECORD_1));
//
//        Mockito.when(tutorService.updateTutorDetails(RECORD_1.getTeacherId(),updateTeachersDetails))
//                .thenReturn(java.util.Optional.of(RECORD_1));
//
//        String updatedContent = objectWriter.writeValueAsString(updateTeachersDetails);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                .put("/updateBook/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(updatedContent);
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",notNullValue()))
//                .andExpect(jsonPath("$.firstName", is("Update TestCase")));
//
//    }


}
