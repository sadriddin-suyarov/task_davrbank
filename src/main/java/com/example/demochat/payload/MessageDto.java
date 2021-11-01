package com.example.demochat.payload;

import lombok.Data;

@Data
public class MessageDto {
    private Integer chat;
    private Integer author;
    private String text;
}
