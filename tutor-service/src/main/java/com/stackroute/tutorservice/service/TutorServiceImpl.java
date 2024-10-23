package com.stackroute.tutorservice.service;

import com.stackroute.tutorservice.model.TeachersDetails;
import com.stackroute.tutorservice.repository.TutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepo repo;


    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public TeachersDetails addTutorDetails(TeachersDetails teachersDetails) {

        try {
            return repo.save(teachersDetails);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<TeachersDetails> readTutorDetails() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Optional<TeachersDetails> updateTutorDetails(int id,TeachersDetails teachersDetails) {
        try {
                Query query = new Query();
            TeachersDetails existingData = mongoOperation
                    .findOne(query.addCriteria(Criteria.where("_id")
                    .is(id)),TeachersDetails.class);

            existingData.setSubject(teachersDetails.getSubject());
            existingData.setMobileNumber(teachersDetails.getMobileNumber());
            existingData.setEmailId(teachersDetails.getEmailId());
            existingData.setAge(teachersDetails.getAge());
            existingData.setQualification(teachersDetails.getQualification());
            existingData.setYearOfExperience(teachersDetails.getYearOfExperience());
            mongoOperation.save(existingData);
            return repo.findById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> deleteTutor(int id) {

        try {
            long beforeDelete = repo.count();

            repo.deleteById(id);

            long afterDelete = repo.count();

            String messageValue = beforeDelete == afterDelete ? "Something went wrong!" : "Record deleted";

            Map<String, String> deleteMap = new HashMap<>();
            deleteMap.put("message", messageValue);

            return deleteMap;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Optional<TeachersDetails> findTeacherById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
