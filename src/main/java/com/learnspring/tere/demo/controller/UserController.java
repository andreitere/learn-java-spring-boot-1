package com.learnspring.tere.demo.controller;

import com.learnspring.tere.demo.dto.UserDTO;
import com.learnspring.tere.demo.model.User;
import com.learnspring.tere.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping({"", "/"})
    public User addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());

        return userRepository.save(user);
    }

//    @GetMapping("/map")
//    public List<Object> getMapped() {
//        return []
//    }
}
