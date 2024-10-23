package com.stackroute.enrollmentservice.model;

import lombok.*;
import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name="student_enrollment")
public class Enrollment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer enrollmentId;
    private String enrollmentDesc;
    private Integer studentId;
    private String studentName;
    private Integer sessionId;
    private String sessionTitle;
    private Integer teacherId;
    private String teacherName;



}

