package com.stackroute.authenticationservice.controller;
import com.stackroute.authenticationservice.config.MessageConfig;
import com.stackroute.authenticationservice.entity.AuthRequest;
import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.exceptions.CustomerNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import com.stackroute.authenticationservice.service.UserService;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Authentication-service")
public class Controller {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
     private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
public User registerNewUser(@RequestBody User user) throws CustomerNotFoundException {

   try {
       if(user==null) {
           throw new CustomerNotFoundException("user not found ");

       }else{
           template.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,user);
           template.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY2,user);
           User regUser=  userService.addUser(user);

           return regUser;
       }
}catch(Exception e) {
   }
   return null;
}

@GetMapping("/getAllUsers")
public List<User> getAllUsers() throws CustomerNotFoundException{
        try {
            return userService.getAllUser();

        }catch(CustomerNotFoundException e){

            return null;
        }
}


    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        }catch (Exception e){
            throw new CustomerNotFoundException("invalid userName/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
   }








}