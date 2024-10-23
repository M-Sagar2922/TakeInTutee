package com.stackroute.enrollmentservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EnrollException.class)
    public ResponseEntity<?> handleEnrollException(EnrollException ex, WebRequest request)
    {
        ExceptionDetails errorDetails=new ExceptionDetails(ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(DisEnrollmentException.class)
    public ResponseEntity<?> handleDisEnrollException(DisEnrollmentException ex, WebRequest request)
    {
        ExceptionDetails errorDetails=new ExceptionDetails(ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<?> handleResourseNotFoundException(EnrollmentNotFoundException ex, WebRequest request)
    {
        ExceptionDetails errorDetails=new ExceptionDetails(ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

}
