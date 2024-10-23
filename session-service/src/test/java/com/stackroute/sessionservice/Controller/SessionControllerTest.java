package com.stackroute.sessionservice.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackroute.sessionservice.controller.SessionController;


import com.stackroute.sessionservice.model.Session;
import com.stackroute.sessionservice.service.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
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

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionController sessionController;

    Session record1 = new Session("1", 1, "Math", "Add&Substract", "MathTopics", 10, 150, new Date(2022 - 10 - 19), "1pm", "2 hr");

    Session record2 = new Session("2", 2, "Science", "Climates", "ScienceTopics", 20, 200, new Date(2022 - 10 - 19), "1pm", "2 hr");

    Session record3 = new Session("3", 3, "History", "Culture", "HistoryTopics", 25, 300, new Date(2022 - 10 - 19), "1pm", "2hr");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sessionController).build();
    }

    @Test
    public void findAllRecord_success() throws Exception {
        List<Session> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
        Mockito.when(sessionService.getAllSessions()).thenReturn(records);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/sessions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].sessionTopics", is("History")));

    }

    @Test
    public void findSessionById_success() throws Exception {


        Mockito.when(sessionService.getSessionById(record1.getSessionId()))
                .thenReturn(record1);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/session/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.sessionTopics", is("Math")));


    }

    @Test
    public void createRecord_success() throws Exception {

        Session record4 = Session.builder()
                .sessionId("4")
                .teacherId(4)
                .sessionTopics("English")
                .description("Grammer")
                .sessionTitle("EnglishTopics")
                .numberOfParticipants(30)
                .sessionFees(350)
                .scheduleSession(new Date(2022 - 10 - 19))
                .scheduleStartTime("1pm")
                .sessionDuration("1hr")
                .build();

        Mockito.when(sessionService.createSession(record4)).thenReturn(record4);

        String content = objectWriter.writeValueAsString(record4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/createSession")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/session/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateRecord_success() throws Exception {

        Session updateRecord = Session.builder()
                .sessionId("4")
                .teacherId(4)
                .sessionTopics("updateTopicEnglish")
                .description("Lessons")
                .sessionTitle("EnglishTopicsUpdated")
                .numberOfParticipants(30)
                .sessionFees(350)
                .build();

        Mockito.when(sessionService.getSessionById(updateRecord.getSessionId()))
                .thenReturn(updateRecord);
        Mockito.when(sessionService.createSession(updateRecord)).thenReturn(updateRecord);

        String updatedContent = objectWriter.writeValueAsString(updateRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/updateSession/4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/session/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.description", is("Lessons")));
    }

    @Test
    public void deleteRecord_success() throws Exception {
        Mockito.when(sessionService.getSessionById(record2.getSessionId()))
                .thenReturn(record2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/session/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
