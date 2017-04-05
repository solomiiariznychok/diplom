package com.riznuchok.service;

import com.riznuchok.entity.User;
import com.riznuchok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registration(User user){
        if(userRepository.findByEmail(user.getEmail())!= null) {
            throw new RuntimeException("user already exist");
        }

        return userRepository.save(user);

    }

}
