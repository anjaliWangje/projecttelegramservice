package com.example.telegram.util;

import com.example.telegram.exception.TelegramException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelegramUtil {

    public static void sendTelegramMessage(String botToken, String chatId, String message) {
        try {
            if (message == null || message.isEmpty()) {
                throw new TelegramException("Message cannot be empty");
            }

            String urlString = String.format(
                    "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    botToken, chatId, message.replace(" ", "%20"));

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new TelegramException("Failed to send message. Response code: " + responseCode);
            }

        } catch (IOException e) {
            throw new TelegramException("Error while sending Telegram message: " + e.getMessage());
        }
    }
}
