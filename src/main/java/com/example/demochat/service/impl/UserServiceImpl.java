package com.example.demochat.service.impl;

import com.example.demochat.entity.User;
import com.example.demochat.exeption.ObjectExistsException;
import com.example.demochat.exeption.ResourceNotFoundException;
import com.example.demochat.payload.UserDto;
import com.example.demochat.repository.UserRepository;
import com.example.demochat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        if (!(userRepository.findAll().size() > 0)) {
            throw new ResourceNotFoundException("Users not found");
        }
        return userRepository.findAll();
    }

    @Override
    public String add(UserDto username) {
        boolean exists = userRepository.existsByUsername(username.getUsername());
        if (exists) {
            throw new ObjectExistsException("User with given username already exists");
        }
        User user = new User();
        user.setUsername(username.getUsername());
        User savedUser = userRepository.save(user);
        return "Success! user id: " + savedUser.getId().toString() + "\n";
    }
}
