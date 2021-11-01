package com.example.demochat.service;

import com.example.demochat.entity.Message;
import com.example.demochat.payload.ChatIdDto;
import com.example.demochat.payload.MessageDto;

import java.util.List;

public interface MessageService {

    String sendMessage(MessageDto messageDto);

    List<Message> getAllByChatId(ChatIdDto userId);

    List<Message> getAll();

}
