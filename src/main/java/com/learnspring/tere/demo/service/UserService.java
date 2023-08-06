package com.learnspring.tere.demo.service;

import com.learnspring.tere.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository) {
    }

}
