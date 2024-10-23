package com.stackroute.studentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "User_Auth_Table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_Name", length = 45, unique = true, nullable = false)
    private String userName;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,12}", message = "password should have at least 8 and maximum 12 characters")
    @Column(name = "password", length = 20, unique = true, nullable = false)
    private String password;
    private Role role;
    private String email;

}