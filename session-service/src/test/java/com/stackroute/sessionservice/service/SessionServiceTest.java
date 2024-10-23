package com.stackroute.sessionservice.service;


import com.stackroute.sessionservice.model.Session;
import com.stackroute.sessionservice.repository.SessionRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class SessionServiceTest {


    @Autowired
    private SessionRepository sessionRepo;

    @Autowired
    private SessionService sessionService;

    @Autowired
    MongoTemplate mongoTemplate;


    Session record1 = new Session("1", 1, "Math", "Add&Substract", "MathTopics", 10, 150,new Date("2022-10-19"),"1pm","2hr");

    Session record2 = new Session("2", 2, "Science", "Climates", "ScienceTopics", 20, 200,new Date("2022-10-19"),"1pm","2hr");

    Session record3 = new Session("3", 3, "History", "Culture", "HistoryTopics", 25, 300,new Date("2022-10-19"),"1pm","2hr");


    @Test
    @DisplayName("Post event Service Method Success")
    public void testPostSessionSuccess() {
        //given

        var testEvent = record1;
        SessionService sessionRepo1 = org.mockito.Mockito.mock(SessionService.class);
        when(sessionRepo1.createSession(record1)).thenReturn(record1);
        //when
        var savedEvent = sessionRepo1.createSession(testEvent);

        //then

        then(savedEvent.getSessionTopics())
                .as("Check the session topics for the input event with the saved event")
                .isEqualTo(testEvent.getSessionTopics());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(savedEvent.getSessionTopics())
                    .as("Check the session topics for the input event with the saved event")
                    .isEqualTo(testEvent.getSessionTopics());

        });

    }

    @Test
    @DisplayName("Find event by Id - Success")
    public void testFindSessionByIdSuccess() {
        //given
        Session testEvent = record2;

        SessionService sessionSevice = org.mockito.Mockito.mock(SessionService.class);
        when(sessionSevice.getSessionById(record2.getSessionId()))
                .thenReturn(record2);
        //when
        var findEvent = sessionSevice.getSessionById(record2.getSessionId());

        //then

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(findEvent.getSessionTopics())
                    .as("Check the session topics ")
                    .isEqualTo(testEvent.getSessionTopics());

        });
    }

    @Test
    @DisplayName("Test - GetAllSession success")
    public void testGetAllSessionSuccess() {
        //given

        List<Session> records = new ArrayList<>(Arrays.asList(record1, record2, record3));

        var testEvent = record3;

        SessionService mock = org.mockito.Mockito.mock(SessionService.class);

        when(mock.getAllSessions()).thenReturn(records);
        //when
        List<Session> findEvents = mock.getAllSessions();

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(findEvents.size())
                    .as("Check count of the Events found")
                    .isEqualTo(3);
        });
    }
}
