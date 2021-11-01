package com.example.demochat.payload;

import lombok.Data;

import java.util.List;

@Data
public class ChatDto {
    private String name;
    private List<Integer> users;
}
