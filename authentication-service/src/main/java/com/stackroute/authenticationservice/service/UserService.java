package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    String forgotPassword(Map<String,String > requestMap);

    User addUser(User user);

    List<User> getAllUser();
}
