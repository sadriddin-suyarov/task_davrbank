package com.example.demochat.service;

import com.example.demochat.entity.User;
import com.example.demochat.payload.UserDto;

import java.util.List;

public interface UserService {
    String add(UserDto username);

    List<User> getAll();

}
