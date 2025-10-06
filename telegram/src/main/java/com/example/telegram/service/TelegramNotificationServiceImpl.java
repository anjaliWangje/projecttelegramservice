package com.example.telegram.service;

import com.example.telegram.dto.NotificationRequestDto;
import com.example.telegram.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(TelegramNotificationServiceImpl.class);

    @Value("${telegram.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public TelegramNotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseDto send(NotificationRequestDto request) {
        try {
            for (String chatId : request.getChatIds()) {
                Map<String, String> body = new HashMap<>();
                body.put("chat_id", chatId);
                body.put("text", request.getMessage());

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

                restTemplate.postForObject(apiUrl, entity, String.class);

                logger.info("✅ Message sent successfully to chatId={}", chatId);
            }
            return new ResponseDto("200", "Message sent successfully");
        } catch (Exception e) {
            logger.error("❌ Failed to send Telegram message", e);
            return new ResponseDto("500", "Failed to send Telegram message: " + e.getMessage());
        }
    }
}
