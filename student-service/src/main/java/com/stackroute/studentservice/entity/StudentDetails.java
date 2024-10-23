package com.stackroute.studentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "StudentService")
@Builder
public class StudentDetails {
    @Id
    private long Id;
    private String fristName;
    private String lastname;
    private String emailId;
    private long mobile;
    private Integer age;
    private String gender;
    private String course;
    private String subject;
    private String address;

}
