package com.stackroute.emailservice.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailServiceDTO {
    @Email(message = "Email not valid")
    private String recipient;
    @NotEmpty(message = "subject does not empty")
    @NotNull(message = "subject does not null")
    private String subject;
    private String message;
    private MultipartFile attachment;

}
