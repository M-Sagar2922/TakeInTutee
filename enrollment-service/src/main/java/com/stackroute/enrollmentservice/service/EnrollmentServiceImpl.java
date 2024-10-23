package com.stackroute.enrollmentservice.service;

import com.stackroute.enrollmentservice.Exception.DisEnrollmentException;
import com.stackroute.enrollmentservice.Exception.EnrollException;

import com.stackroute.enrollmentservice.Exception.EnrollmentNotFoundException;
import com.stackroute.enrollmentservice.dao.EnrollmentDaoImpl;
import com.stackroute.enrollmentservice.model.Enrollment;
import com.stackroute.enrollmentservice.rabbitMQ.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentDaoImpl enrollDaoImpl;

    @Autowired
    private Producer producer;

    @Override
    @Transactional
    public Enrollment sessionEnroll(Enrollment enrollRequest) throws EnrollException {
        Enrollment studentEnrollment=enrollDaoImpl.findByStudentIdAndSessionId(enrollRequest.getStudentId(), enrollRequest.getSessionId());
        if(studentEnrollment==null) {
            Enrollment enrollment=enrollDaoImpl.save(enrollRequest);
            producer.sendMessageToRabbitMq(enrollment);
            return (enrollment);
        }
        else
            throw new EnrollException("Already Enroll For Session");
    }

    @Override
    public Optional<String> sessionDisEnroll(Integer enrollmentId) throws DisEnrollmentException {
        try {
            enrollDaoImpl.deleteById(enrollmentId);
            return Optional.of("Session DisEnrollment Successfully");
        } catch (Exception ex) {
            throw new DisEnrollmentException("dis enrollment failed");
        }
    }

    @Override
    public Optional<Integer> getEnrollId(Integer studentId,Integer sessionId) throws EnrollmentNotFoundException {
        Enrollment enrollment=enrollDaoImpl.findByStudentIdAndSessionId(studentId,sessionId);
       if(enrollment!=null)
             return Optional.ofNullable(enrollment.getEnrollmentId());
       else
           throw new EnrollmentNotFoundException("Enrollment Id Does Not Exist");
    }

}
