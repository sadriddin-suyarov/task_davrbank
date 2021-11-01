package com.example.demochat.controller;

import com.example.demochat.entity.User;
import com.example.demochat.payload.UserDto;
import com.example.demochat.repository.UserRepository;
import com.example.demochat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }
}
