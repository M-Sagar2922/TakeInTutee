package com.stackroute.emailservice.repository;


import com.stackroute.emailservice.rabbitmq.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<StudentDTO,Integer> {
    public List<StudentDTO> findByStudentEmail(String studentEmail);
}
