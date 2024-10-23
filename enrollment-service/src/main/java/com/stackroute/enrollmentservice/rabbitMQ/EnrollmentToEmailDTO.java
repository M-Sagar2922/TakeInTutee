package com.stackroute.enrollmentservice.rabbitMQ;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EnrollmentToEmailDTO {
        private Integer studentId;
        private String studentName;
        private String tutorName;
        private String sessionName;
        private Integer enrollmentId;
}