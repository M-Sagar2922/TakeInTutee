package com.stackroute.tutorservice.service;

import com.stackroute.tutorservice.model.TeachersDetails;
import com.stackroute.tutorservice.repository.TutorRepo;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class ServiceTest {
    @Autowired
    private TutorService tutorService;

    @Autowired
    private TutorRepo tutorRepo;

    @Autowired
    MongoTemplate mongoTemplate;


    TeachersDetails RECORD_1 = new TeachersDetails(1,"Fatha","JKD","Fatha@gmail.com"
            ,26,"Male","9876543212",5,"Java","Engineering");

    TeachersDetails RECORD_2 = new TeachersDetails(2,"Mehboob","Jamadar","mehboob@gmail.com"
            ,28,"Male","78957634524",7,"Big Data","B.E");

    TeachersDetails RECORD_3 = new TeachersDetails(3,"Yumin","Gu","yumin@gmail.com"
            ,32,"Female","2138623567",10,"AWS","B.Tech");

    @Test
    @DisplayName("Post event Service Method Success")
    public void testPostTeacherDetailsSuccess(){
        //given

        var testEvent = RECORD_1;
        TutorService tutorRepo1 = org.mockito.Mockito.mock(TutorService.class);
        when(tutorRepo1.addTutorDetails(RECORD_1)).thenReturn(RECORD_1);
        //when
        var savedEvent = tutorRepo1.addTutorDetails(testEvent);

        //then

        then(savedEvent.getFirstName())
                .as("Check the name for the input event with the saved event")
                .isEqualTo(testEvent.getFirstName());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(savedEvent.getFirstName())
                    .as("Check the name for the input event with the saved event")
                    .isEqualTo(testEvent.getFirstName());
            softly.assertThat(savedEvent.getEmailId())
                    .as("Check the description for the input event with the saved event")
                    .isEqualTo(testEvent.getEmailId());
            softly.assertThat(savedEvent.getEmailId())
                    .as("Check the Id exists")
                    .isNotEmpty();
        });

    }

    @Test
    @DisplayName("Find event by Id - Success")
    public void testFindTeacherByIdSuccess(){
        //given
        TeachersDetails testEvent = RECORD_2;

        TutorService tutorSevice = org.mockito.Mockito.mock(TutorService.class);
        when(tutorSevice.findTeacherById(RECORD_2.getTeacherId()))
                .thenReturn(Optional.ofNullable(RECORD_2));
        //when
        var findEvent = tutorSevice.findTeacherById(RECORD_2.getTeacherId());

        //then

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(testEvent.getFirstName())
                    .as("Check the name for match")
                    .isEqualTo(findEvent.get().getFirstName());
            softly.assertThat(testEvent.getEmailId())
                    .as("Check the description for match")
                    .isEqualTo(findEvent.get().getEmailId());
        });
    }

    @Test
    @DisplayName("Test - GetAllTeacherDetails success")
    public void testGetAllTeacherDetails(){
        //given

        List<TeachersDetails> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        var testEvent = RECORD_3;

        TutorService mock = org.mockito.Mockito.mock(TutorService.class);

        when(mock.readTutorDetails()).thenReturn(records);
        //when
        List<TeachersDetails> findEvents = mock.readTutorDetails();

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(findEvents.size())
                    .as("Check count of the Events found")
                    .isEqualTo(3);
        });
    }

}
