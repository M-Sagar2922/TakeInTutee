package com.stackroute.authenticationservice.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class    CustomerNotFoundException extends UsernameNotFoundException {
    public CustomerNotFoundException(String message){
        super(message);
    }

}
