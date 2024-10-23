package com.stackroute.sessionservice.repository;

import com.stackroute.sessionservice.model.Session;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {

}
