package com.example.demochat.service.impl;

import com.example.demochat.entity.Chat;
import com.example.demochat.entity.User;
import com.example.demochat.exeption.ObjectExistsException;
import com.example.demochat.exeption.ResourceNotFoundException;
import com.example.demochat.payload.ChatDto;
import com.example.demochat.payload.UserIdDto;
import com.example.demochat.repository.ChatRepository;
import com.example.demochat.repository.UserRepository;
import com.example.demochat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Chat> getAll() {
        if (!(chatRepository.findAll().size() > 0)) {
            throw new ResourceNotFoundException("Chats not found");
        }
        return chatRepository.findAll();
    }

    @Override
    public String add(ChatDto chatDto) {
        boolean exists = chatRepository.existsByName(chatDto.getName());
        if (exists) {
            throw new ObjectExistsException("Chat with given name already exists");
        }

        List<User> chatUsers = new ArrayList<>();
        List<Integer> chatDTOUserId = chatDto.getUsers();

        for (Integer userId : chatDTOUserId) {
            boolean userExists = userRepository.findById(userId).isPresent();
            if (userExists) {
                chatUsers.add(userRepository.findById(userId).get());
            }
        }

        Chat chat = new Chat();
        chat.setName(chatDto.getName());
        chat.setUsers(chatUsers);
        Chat savedChat = chatRepository.save(chat);
        return "Success! chat id: " + savedChat.getId().toString() + "\n";

    }

    @Override
    public List<Chat> getAllByUserId(UserIdDto userId) {
        boolean userExists = userRepository.existsById(userId.getUserId());//        Check whether user exists
        if (!userExists) {
            throw new ResourceNotFoundException("User with given user_id not found");
        }

        int rowCount = chatRepository.getRowCount();

        if (!(rowCount > 0)) {
            throw new ResourceNotFoundException("Chat with given user_id not found");
        }

        return chatRepository.getAllByUserIdOrderedByCreatedAt(userId.getUserId());
    }
}
