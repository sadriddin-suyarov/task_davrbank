package com.example.demochat.controller;

import com.example.demochat.entity.Chat;
import com.example.demochat.payload.ChatDto;
import com.example.demochat.payload.UserIdDto;
import com.example.demochat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {


    @Autowired
    ChatService chatService;

    @GetMapping
    public List<Chat> getAll() {
        return chatService.getAll();
    }


    @PostMapping("/add")
    public String add(@RequestBody ChatDto chatDTO) {
        return chatService.add(chatDTO);
    }

    @PostMapping("/get")
    public List<Chat> getAllByUserId(@RequestBody UserIdDto userIdDto) {
        return chatService.getAllByUserId(userIdDto);
    }
}
