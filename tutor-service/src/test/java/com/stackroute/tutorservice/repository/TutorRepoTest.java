package com.stackroute.tutorservice.repository;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.stackroute.tutorservice.model.TeachersDetails;
import com.stackroute.tutorservice.service.TutorService;
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
public class TutorRepoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TutorRepo tutorRepo;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() throws Exception{
        Path resourceDirectory = Paths.get("src","test","resources");
        Path tstRes = resourceDirectory.resolve("data/eventCreate.json");
        String absolutePath = tstRes.toFile().getAbsolutePath();
        File file = new File(absolutePath);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        TeachersDetails[] events = mapper.readValue(file,TeachersDetails[].class);
        Arrays.stream(events).forEach(mongoTemplate::save);
    }

    @AfterEach
    void destroy(){
        //mongoTemplate.dropCollection("Events");
    }


    @Test
    @DisplayName("Repository - Create Event")
    public void createEvent() throws  Exception{
        //given
        var testEvent = new TeachersDetails(1,"Fatha","JKD","Fatha@gmail.com"
                ,26,"Male","9876543212",5,"Java","Engineering");

        TutorRepo tutorRepo1 = org.mockito.Mockito.mock(TutorRepo.class);
        Mockito.when(tutorRepo1.save(any())).thenReturn(testEvent);
        // when
        var savedEvent = tutorRepo1.save(testEvent);

        //then
        then(savedEvent.getTeacherId())
                .as("Check if the Id generated for the saved Event")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Repository - Find Event by Id")
    public void testFindEventsById(){

        //given
        var testEvent = new TeachersDetails(1,"Fatha","JKD","Fatha@gmail.com"
                ,26,"Male","9876543212",5,"Java","Engineering");

        TutorRepo tutorRepo1 = org.mockito.Mockito.mock(TutorRepo.class);
        Mockito.when(tutorRepo1.findById(any())).thenReturn(Optional.of(testEvent));

        // when
        Optional<TeachersDetails> fetchEvent = tutorRepo1.findById(1);

        //then

        then(1)
                .as("Validate the Id as saved same find.")
                .isEqualTo(fetchEvent.get().getTeacherId());
    }
}
