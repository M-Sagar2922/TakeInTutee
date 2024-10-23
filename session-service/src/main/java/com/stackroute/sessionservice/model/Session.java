package com.stackroute.sessionservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Document(collection = "Session")
public class Session {


    @Id
    private String sessionId;
    private Integer teacherId;
    private String sessionTopics;
    private String description;
    private String sessionTitle;
    private Integer numberOfParticipants;
    private Integer sessionFees;
    private Date scheduleSession;
    private String scheduleStartTime;
    private String sessionDuration;
}
