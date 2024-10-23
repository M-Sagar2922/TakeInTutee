package com.stackroute.tutorservice.repository;

import com.stackroute.tutorservice.model.TeachersDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TutorRepo extends MongoRepository<TeachersDetails, Integer> {

}
