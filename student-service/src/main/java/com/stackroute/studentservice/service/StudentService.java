package com.stackroute.studentservice.service;

import com.stackroute.studentservice.entity.StudentDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {


    StudentDetails addStudentDetails(String studentDetails);

    List<StudentDetails> readStudentDetails();
    Optional<StudentDetails> updateStudentDetails(int id, StudentDetails studentDetails);

    Map<String, String> deleteStudent(int id);


    Optional<StudentDetails> findStudentById(int id);

}
