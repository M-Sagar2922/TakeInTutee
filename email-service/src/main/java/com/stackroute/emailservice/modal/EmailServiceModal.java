package com.stackroute.emailservice.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailServiceModal {
    private String recipient;
    private String message;
    private String subject;
    private MultipartFile attachment;

    public String getRecipient() {
        return null;
    }
}
