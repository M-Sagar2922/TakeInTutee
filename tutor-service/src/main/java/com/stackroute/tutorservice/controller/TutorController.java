package com.stackroute.tutorservice.controller;

//import com.stackroute.tutorservice.config.MessagingConfig;
import com.stackroute.tutorservice.model.TeachersDetails;

import com.stackroute.tutorservice.service.TutorService;


import com.sun.istack.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;
import java.util.Optional;


// Annotation
@RestController
@RequestMapping("/api/v1/Tutor-service")
public class TutorController {

    @Autowired
    private TutorService reposervice;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to TakeinTutee Application";
    }

//    @PostMapping("/addTeacher")
//    public TeachersDetails insertTutor(@RequestBody TeachersDetails teachersDetails) {
//        template.convertAndSend(MessagingConfig.SESSION_EXCHANGE, MessagingConfig.SESSION_ROUTING_KEY, teachersDetails.getTeacherId());
//        template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.SESSION_ROUTING_KEY,teachersDetails.getEmailId());
//        return reposervice.addTutorDetails(teachersDetails);
//    }


    @PutMapping("/updateBook/{id}")
    public Optional<TeachersDetails> updateTutor(@PathVariable @NotNull int id,
                                                 @RequestBody TeachersDetails teachersDetails) {
        return reposervice.updateTutorDetails(id,teachersDetails);
    }

    @GetMapping("/findAllTutors")
    public List<TeachersDetails> getTutors() {
        return reposervice.readTutorDetails();
    }

    @GetMapping("/findById/{id}")
    public Optional<TeachersDetails> searchTutorById(@PathVariable int id) {
        return reposervice.findTeacherById(id);

    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteTutor(@PathVariable int id) {
        return reposervice.deleteTutor(id);
    }
}

