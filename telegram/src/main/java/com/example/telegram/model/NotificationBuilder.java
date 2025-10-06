package com.example.telegram.model;

import java.time.LocalDateTime;

public class NotificationBuilder {
    private Long id;
    private String message;
    private String status;
    private LocalDateTime createdAt;

    public NotificationBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public NotificationBuilder message(String message) {
        this.message = message;
        return this;
    }

    public NotificationBuilder status(String status) {
        this.status = status;
        return this;
    }

    public NotificationBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Notification build() {
        return new Notification(id, message, status, createdAt);
    }
}
