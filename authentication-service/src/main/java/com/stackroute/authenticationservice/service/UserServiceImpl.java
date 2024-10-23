package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;


    @Override
    public String forgotPassword(Map<String, String> requestMap) {
       User user =repository.findByEmail(requestMap.get("email"));
        return null;
    }

    @Override
    public User addUser(User user) {
      User addUser=  repository.save(user);
        return addUser;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }
}
