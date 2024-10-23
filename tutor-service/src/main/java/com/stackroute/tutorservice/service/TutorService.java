package com.stackroute.tutorservice.service;

import com.stackroute.tutorservice.model.TeachersDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TutorService {

    TeachersDetails addTutorDetails(TeachersDetails teachersDetails);

     List<TeachersDetails>  readTutorDetails();

    Optional<TeachersDetails> updateTutorDetails(int id,TeachersDetails teachersDetails);

    Map<String, String> deleteTutor(int id);

    Optional<TeachersDetails> findTeacherById(int id);

}
