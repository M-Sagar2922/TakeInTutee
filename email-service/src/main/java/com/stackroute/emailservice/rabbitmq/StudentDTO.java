package com.stackroute.emailservice.rabbitmq;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="enrollment_details")
public class StudentDTO {
    @Id
    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private String tutorName;
    private String sessionName;
    private Double sessionFee;
    private Integer enrollmentId;
}
