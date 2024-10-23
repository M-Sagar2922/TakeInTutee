package com.stackroute.tutorservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TutorService")
@Builder
public class TeachersDetails {
    @Id
    private int teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private String emailId;
    private int age;
    private String gender;
    private String mobileNumber;
    private int YearOfExperience;
    private String subject;
    private String qualification;

}
