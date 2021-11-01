package com.example.demochat.service;

import com.example.demochat.entity.Chat;
import com.example.demochat.payload.ChatDto;
import com.example.demochat.payload.UserIdDto;

import java.util.List;

public interface ChatService {

    String add(ChatDto chatDto);

    List<Chat> getAllByUserId(UserIdDto userId);

    List<Chat> getAll();
}
