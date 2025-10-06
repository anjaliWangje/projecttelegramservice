package com.example.telegram.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class NotificationRequestDto {
    @NotNull(message = "chatIds must not be null")
    @NotEmpty(message = "chatIds must contain at least one id")
    private List<String> chatIds;


    @NotNull(message = "message must not be null")
    @NotEmpty(message = "message must not be empty")
    private String message;


    public List<String> getChatIds() {
        return chatIds;
    }


    public void setChatIds(List<String> chatIds) {
        this.chatIds = chatIds;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}

