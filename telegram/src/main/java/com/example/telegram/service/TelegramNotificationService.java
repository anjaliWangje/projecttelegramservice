package com.example.telegram.service;


import com.example.telegram.dto.NotificationRequestDto;
import com.example.telegram.dto.ResponseDto;

public interface TelegramNotificationService {
    ResponseDto send(NotificationRequestDto request);
}

