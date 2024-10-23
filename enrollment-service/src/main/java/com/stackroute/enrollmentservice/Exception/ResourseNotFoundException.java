package com.stackroute.enrollmentservice.Exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String msg)
    {
        super(msg);
    }
}
