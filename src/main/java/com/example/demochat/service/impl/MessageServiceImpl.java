package com.example.demochat.service.impl;

import com.example.demochat.entity.Chat;
import com.example.demochat.entity.Message;
import com.example.demochat.entity.User;
import com.example.demochat.exeption.ResourceNotFoundException;
import com.example.demochat.payload.ChatIdDto;
import com.example.demochat.payload.MessageDto;
import com.example.demochat.repository.ChatRepository;
import com.example.demochat.repository.MessageRepository;
import com.example.demochat.repository.UserRepository;
import com.example.demochat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public String sendMessage(MessageDto messageDto) {
        boolean authorExists = userRepository.findById(messageDto.getAuthor()).isPresent();
        boolean chatExists = chatRepository.findById(messageDto.getChat()).isPresent();
        if (!authorExists) {
            throw new ResourceNotFoundException("User with given id not found");
        }
        if (!chatExists) {
            throw new ResourceNotFoundException("Chat with given id not found");
        }
        Chat chat = chatRepository.findById(messageDto.getChat()).get();
        User author = userRepository.findById(messageDto.getAuthor()).get();
        Message message = new Message();
        message.setChat(chat);
        message.setAuthor(author);
        message.setText(messageDto.getText());
        Message savedMessage = messageRepository.save(message);
        return "Success! message id: " + savedMessage.getId().toString() + "\n";
    }

    @Override
    public List<Message> getAll() {
        if (!(messageRepository.findAll().size() > 0)) {
            throw new ResourceNotFoundException("Messages not found");
        }
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getAllByChatId(ChatIdDto chatIdDto) {
        boolean chatExists = chatRepository.existsById(chatIdDto.getChat());
        if (!chatExists) {
            throw new ResourceNotFoundException("Chat with given chat_id not found");
        }

        List<Message> result = messageRepository.findAllByChatId(chatIdDto.getChat());
        if (!(result.size() > 0)) {
            throw new ResourceNotFoundException("Message with given chat_id not found");
        }

        return result;
    }
}
