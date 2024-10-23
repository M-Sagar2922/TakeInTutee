package com.stackroute.studentservice.service;

import com.stackroute.studentservice.entity.StudentDetails;
import com.stackroute.studentservice.repository.StudentRepo;
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
//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private StudentRepo studentRepo;
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//
//    StudentDetails RECORD_1 = new StudentDetails(1,"vikash","pawar","vikash@gmail.com",1234567890,
//            22,"male","ECE","java","ambedkar nagar");
//
//    StudentDetails RECORD_2 = new StudentDetails(2,"Suraj","singh","suraj@gmail.com",1122334455,
//            23,"male","ECE","python","ambedkar nagar");
//
//    StudentDetails RECORD_3 = new StudentDetails( 3,"Sagar","kalbhor","sagar@gmail.com",1234567890,
//            24,"male","ECE","AWS","ambedkar nagar");
//
//
//    @Test
//    @DisplayName("Post event Service Method Success")
//    public void testPostStudentDetailsSuccess(){
//        //given
//
//        var testEvent = RECORD_1;
//        StudentService studentRepo1 = org.mockito.Mockito.mock(StudentService.class);
//        when(studentRepo1.addStudentDetails(RECORD_1)).thenReturn(RECORD_1);
//        //when
//        var savedEvent = studentRepo1.addStudentDetails(testEvent);
//
//        //then
//
//        then(savedEvent.getFristName())
//                .as("Check the name for the input event with the saved event")
//                .isEqualTo(testEvent.getFristName());
//
//        SoftAssertions.assertSoftly(softly -> {
//            softly.assertThat(savedEvent.getFristName())
//                    .as("Check the name for the input event with the saved event")
//                    .isEqualTo(testEvent.getFristName());
//            softly.assertThat(savedEvent.getEmailId())
//                    .as("Check the description for the input event with the saved event")
//                    .isEqualTo(testEvent.getEmailId());
//            softly.assertThat(savedEvent.getEmailId())
//                    .as("Check the Id exists")
//                    .isNotEmpty();
//        });
//
//    }
//
//    @Test
//    @DisplayName("Find event by Id - Success")
//    public void testFindStudentByIdSuccess(){
//        //given
//        StudentDetails testEvent = RECORD_2;
//
//        StudentService studentService = org.mockito.Mockito.mock(StudentService.class);
//        when(studentService.findStudentById((int) RECORD_2.getId()))
//                .thenReturn(Optional.ofNullable(RECORD_2));
//        //when
//        var findEvent = studentService.findStudentById((int) RECORD_2.getId());
//
//        //then
//
//        SoftAssertions.assertSoftly(softly -> {
//            softly.assertThat(testEvent.getFristName())
//                    .as("Check the name for match")
//                    .isEqualTo(findEvent.get().getFristName());
//            softly.assertThat(testEvent.getEmailId())
//                    .as("Check the description for match")
//                    .isEqualTo(findEvent.get().getEmailId());
//        });
//    }
//
//    @Test
//    @DisplayName("Test - GetAllStudentDetails success")
//    public void testGetAllStudentDetails(){
//        //given
//
//        List<StudentDetails> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
//        var testEvent = RECORD_3;
//
//        StudentService mock = org.mockito.Mockito.mock(StudentService.class);
//
//        when(mock.readStudentDetails()).thenReturn(records);
//        //when
//        List<StudentDetails> findEvents = mock.readStudentDetails();
//
//        //then
//        SoftAssertions.assertSoftly(softly -> {
//            softly.assertThat(findEvents.size())
//                    .as("Check count of the Events found")
//                    .isEqualTo(3);
//        });
//    }

}

