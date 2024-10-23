package com.stackroute.studentservice.repository;

import com.stackroute.studentservice.entity.StudentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends MongoRepository<StudentDetails,Integer> {

}
