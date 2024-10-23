package com.stackroute.enrollmentservice.service;

import com.stackroute.enrollmentservice.dao.EnrollmentDaoImpl;
import com.stackroute.enrollmentservice.model.Enrollment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollService;

    @Autowired
    private EnrollmentDaoImpl enrollDaoImpl;

    Enrollment RECORD_1 = new Enrollment(1, "Enrollment For Session1 ", 101,
            "samir", 201, "session1", 301, "vijay");

    Enrollment RECORD_2 = new Enrollment(2, "Enrollment For session2",
            102, "ajay", 202, "session2", 302, "Rakesh");

    Enrollment RECORD_3 = new Enrollment(3, "Enrollment For session3",
            103, "amit", 203, "session3", 303, "vinit");

     @Test
    @DisplayName("Test Case for sessionEnroll method")
    public void testSessionEnrollmentService() throws Exception {

        EnrollmentServiceImpl enrollService = org.mockito.Mockito.mock(EnrollmentServiceImpl.class);

        var EnrollData = RECORD_1;
        EnrollmentService enrollment = org.mockito.Mockito.mock(EnrollmentService.class);
        when(enrollment.sessionEnroll(RECORD_1)).thenReturn(RECORD_1);

        var savedData = enrollment.sessionEnroll(EnrollData);

        then(savedData.getSessionId())
                .as("Check session id is equals to th saved data")
                .isEqualTo(EnrollData.getSessionId());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(savedData.getStudentId())
                    .as("Check student id is equals to th saved data")
                    .isEqualTo(EnrollData.getStudentId());
            softly.assertThat(savedData.getSessionId())
                    .as("Check the description is equal to saved data")
                    .isEqualTo(EnrollData.getSessionId());
        });

    }


    @Test
    @DisplayName("Test Case for sessionDisEnrollment Method")
    public void testSessionDisEnrollment() throws Exception {
        //given

        EnrollmentService enrollService = Mockito.mock(EnrollmentServiceImpl.class);

        when(enrollService.sessionDisEnroll(RECORD_2.getEnrollmentId()))

                .thenReturn(Optional.of("DisEnroll Successfully"));

        assertEquals(Optional.of("DisEnroll Successfully"), enrollService.sessionDisEnroll(RECORD_2.getEnrollmentId()));

    }


    @Test
    @DisplayName("Test Case for getEnrollmentId Method")
    public void testGetEnrollmentId() throws Exception {
        //given


        EnrollmentService enrollService = Mockito.mock(EnrollmentServiceImpl.class);

              when(enrollService.getEnrollId(RECORD_3.getStudentId(),RECORD_3.getSessionId()))

                 .thenReturn(Optional.ofNullable(RECORD_3.getEnrollmentId()));

        var enrollId = enrollService.getEnrollId(RECORD_3.getStudentId(),RECORD_3.getSessionId());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(Optional.of(RECORD_3.getEnrollmentId()))
                    .as("Check Enrollment ID")
                    .isEqualTo(enrollId);
            
        });

    }

}
