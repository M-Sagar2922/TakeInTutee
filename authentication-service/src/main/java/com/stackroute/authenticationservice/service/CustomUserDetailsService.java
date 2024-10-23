package com.stackroute.authenticationservice.service;


import com.stackroute.authenticationservice.entity.AuthRequest;
import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

//    public User checkUserByUsername(String userName){
//        User user = repository.findByUserName(userName);
//        return user;
//    }
//
//    public User addUser(AuthRequest request){
//        User newUser = new User();
//        newUser.setUserName(request.getUserName());
//       // newUser.setEmail(request.getEmail());
//        newUser.setPassword(request.getPassword());
//       // newUser.setRole(request.getRole());
//        newUser =  repository.save(newUser);
//        return newUser;
//    }

}
