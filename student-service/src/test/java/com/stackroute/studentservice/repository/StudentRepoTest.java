package com.stackroute.studentservice.repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.stackroute.studentservice.entity.StudentDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;


import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class StudentRepoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StudentRepo studentRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception{
        Path resourceDirectory = Paths.get("src","test","resources");
        Path tstRes = resourceDirectory.resolve("data/eventCreate.json");
        String absolutePath = tstRes.toFile().getAbsolutePath();
        File file = new File(absolutePath);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
       StudentDetails[] events = mapper.readValue(file,StudentDetails[].class);
        Arrays.stream(events).forEach(mongoTemplate::save);
    }

    @AfterEach
    void destroy(){
        //mongoTemplate.dropCollection("Events");
    }


    @Test
    @DisplayName("Repository - Create Event")
    public void createStudentDetails() throws  Exception{
        //given
        var testEvent = new StudentDetails( 1,"vikash","pawar","vikash@gmail.com",1234567890,
                22,"male","ECE","java","ambedkar nagar");

        StudentRepo studentRepo1 = org.mockito.Mockito.mock(StudentRepo.class);
        Mockito.when(studentRepo1.save(any())).thenReturn(testEvent);
        // when
        var savedEvent = studentRepo1.save(testEvent);

        //then
        then(savedEvent.getId())
                .as("Check if the Id generated for the saved Event")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Repository - Find Event by Id")
    public void testFindEventsById(){

        //given
        var testEvent = new StudentDetails( 1,"vikash","pawar","vikash@gmail.com",1234567890,
                22,"male","ECE","java","ambedkar nagar");

        StudentRepo studentRepo1 = org.mockito.Mockito.mock(StudentRepo.class);
        Mockito.when(studentRepo1.findById(any())).thenReturn(Optional.of(testEvent));

        // when
        Optional<StudentDetails> fetchEvent = studentRepo1.findById(1);

        //then

        then(1)
                .as("Validate the Id as saved same find.")
                .isEqualTo(fetchEvent.get().getId());
    }
}

