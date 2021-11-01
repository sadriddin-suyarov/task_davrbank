package com.example.demochat.controller;

import com.example.demochat.entity.Message;
import com.example.demochat.payload.ChatIdDto;
import com.example.demochat.payload.MessageDto;
import com.example.demochat.payload.UserIdDto;
import com.example.demochat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping("/add")
    public String sendMessage(@RequestBody MessageDto messageDTO) {
        return messageService.sendMessage(messageDTO);
    }


    @PostMapping("/get")
    public List<Message> getAllByChatId(@RequestBody ChatIdDto chatIdDto) {
        return messageService.getAllByChatId(chatIdDto);
    }
}
