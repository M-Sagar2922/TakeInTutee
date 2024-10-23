package com.stackroute.enrollmentservice.dao;

import com.stackroute.enrollmentservice.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentDaoImpl extends JpaRepository<Enrollment,Integer> {
    Enrollment findByStudentIdAndSessionId(Integer studentId, Integer sessionId);
}
