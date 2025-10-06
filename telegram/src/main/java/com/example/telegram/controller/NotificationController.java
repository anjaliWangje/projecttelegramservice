package com.example.telegram.controller;



import com.example.telegram.dto.NotificationRequestDto;
import com.example.telegram.dto.ResponseDto;
import com.example.telegram.service.TelegramNotificationService;
import com.example.telegram.service.TelegramNotificationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
//@RequiredArgsConstructor
public class NotificationController {

    private final TelegramNotificationServiceImpl telegramService;

    public NotificationController(TelegramNotificationServiceImpl telegramService) {
        this.telegramService = telegramService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> sendNotification(@Valid @RequestBody NotificationRequestDto requestDto) {
        // This will call Telegram service (can throw exceptions handled in GlobalExceptionHandler)
        return ResponseEntity.ok(telegramService.send(requestDto));
    }
}
