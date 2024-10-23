package com.stackroute.studentservice.service;

import com.stackroute.studentservice.entity.StudentDetails;
import com.stackroute.studentservice.repository.StudentRepo;
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
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public StudentDetails addStudentDetails(String studentDetails) {

//        try {
//            StudentDetails StudentDetails;
//            return studentRepo.save(studentDetails);
//        }catch (Exception e) {
//            throw e;
//        }
        return null;
    }


    @Override
    public List<StudentDetails> readStudentDetails() {
        try {
            return studentRepo.findAll();
        }catch (Exception e) {
            throw e;
        }
    }


    @Override
    public Optional<StudentDetails> updateStudentDetails(int id, StudentDetails studentDetails) {
        try {
            Query query = new Query();
            StudentDetails existingData = mongoOperations
                    .findOne(query.addCriteria(Criteria.where(" id")
                            .is(id)),StudentDetails.class);

            existingData.setFristName(studentDetails.getFristName());
            existingData.setAge(studentDetails.getAge());
            mongoOperations.save(existingData);
            return studentRepo.findById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> deleteStudent(int id) {
        try{
            long beforeDelete = studentRepo.count();
            studentRepo.deleteById(id);
            long afterDelete = studentRepo.count();
            String messageValue = beforeDelete == afterDelete ? "Something went wrong" : "Record deleted";

            Map<String,String> deleteMap = new HashMap<>();
            deleteMap.put("message",messageValue);
            return deleteMap;
        } catch (Exception e) {
            throw e;
        }

    }


    @Override
    public Optional<StudentDetails> findStudentById(int id) {
        try {
            return studentRepo.findById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
