package com.stackroute.authenticationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthRequest {
@Column()
   private String userName;
    @Pattern(regexp = "\"^[a-zA-Z0-9]{8,12}\",message = \"password should have at least 8 and maximum 12 characters\"")
    private String password;


    public void getPassword(String password) {
    }

    public void getUserName(String userName) {
    }




}
