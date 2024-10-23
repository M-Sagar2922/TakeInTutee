package com.stackroute.enrollmentservice.controller;

import com.stackroute.enrollmentservice.Exception.EnrollException;
import com.stackroute.enrollmentservice.Exception.DisEnrollmentException;
import com.stackroute.enrollmentservice.Exception.EnrollmentNotFoundException;

import com.stackroute.enrollmentservice.model.Enrollment;
import com.stackroute.enrollmentservice.service.EnrollmentServiceImpl;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/Enrollment-service")
public class EnrollmentController {

    @Autowired
    EnrollmentServiceImpl enrollServiceImpl;

   //@Autowired(required = true)
    private DirectExchange directExchange;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/welcome")
    public String greet()
    {
        return "Welcome to Enrollment Service";
    }

    @PostMapping("/enrollment")
    public ResponseEntity<Enrollment> sessionEnrollment(@RequestBody Enrollment enrollRequest) throws EnrollException{
        try {
            return new ResponseEntity<>(enrollServiceImpl.sessionEnroll(enrollRequest), HttpStatus.OK);
        }
        catch (EnrollException ex) {
             throw ex;
          }
    }

    @DeleteMapping("/disEnrollment/{enrollId}")
    public ResponseEntity<Optional<String>> sessionDisEnrollById(@PathVariable("enrollId") Integer enrollmentId) throws DisEnrollmentException
    {
        try{
            return new ResponseEntity<>(enrollServiceImpl.sessionDisEnroll(enrollmentId), HttpStatus.OK);
        }
        catch(DisEnrollmentException ex)
        {
            throw ex;
        }
    }

    @PostMapping("/getEnrollmentId/{studentId}/{sessionId}")
    public ResponseEntity<Optional<Integer>> getEnrollId(@PathVariable("studentId") Integer studentId,@PathVariable("sessionId") Integer sessionId) throws EnrollmentNotFoundException {
        try {
            return new ResponseEntity<>(enrollServiceImpl.getEnrollId(studentId,sessionId), HttpStatus.OK);
        } catch (EnrollmentNotFoundException ex) {
            throw ex;
        }

    }

}