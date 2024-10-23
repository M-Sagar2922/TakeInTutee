package com.stackroute.enrollmentservice.service;

import com.stackroute.enrollmentservice.model.Enrollment;
import java.util.Optional;

public interface EnrollmentService {

    Enrollment sessionEnroll(Enrollment enrollRequest) throws Exception;
    Optional<String> sessionDisEnroll(Integer enrollmentId) throws Exception;
    Optional<Integer>  getEnrollId(Integer studentId,Integer sessionId) throws Exception;
}
