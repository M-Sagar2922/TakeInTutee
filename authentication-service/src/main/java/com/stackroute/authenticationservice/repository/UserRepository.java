package com.stackroute.authenticationservice.repository;


import com.stackroute.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);

   User findByEmail(String email);
}
