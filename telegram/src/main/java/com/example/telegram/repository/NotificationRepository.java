package com.example.telegram.repository;

import com.example.telegram.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


//     default Object saveNotification(Notification notification) {
//        return null;
//    }
}
