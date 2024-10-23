package com.stackroute.enrollmentservice.repository;


import com.stackroute.enrollmentservice.dao.EnrollmentDaoImpl;
import com.stackroute.enrollmentservice.model.Enrollment;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentDaoImpl enrollDaoImpl;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    public void testFindByStudentIdAndSessionId() {

        Enrollment enroll=new Enrollment();
        enroll.setEnrollmentId(3);
        enroll.setEnrollmentDesc("Enrollment for Session1");
        enroll.setStudentId(103);
        enroll.setStudentName("kumar");
        enroll.setSessionId(203);
        enroll.setSessionTitle("session3");
        enroll.setTeacherId(303);
        enroll.setTeacherName("vijay");

        EnrollmentDaoImpl enrollmentDao = org.mockito.Mockito.mock(EnrollmentDaoImpl.class);
        Mockito.when(enrollmentDao.findByStudentIdAndSessionId(enroll.getStudentId(),enroll.getSessionId())).thenReturn(enroll);

        var savedData = enrollmentDao.findByStudentIdAndSessionId(enroll.getStudentId(),enroll.getSessionId());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(enroll.getStudentId())
                    .as("Check student ID")
                    .isEqualTo(savedData.getStudentId());
            softly.assertThat(enroll.getSessionId())
                    .as("Check session ID")
                    .isEqualTo(savedData.getSessionId());

        });

    }



}





















