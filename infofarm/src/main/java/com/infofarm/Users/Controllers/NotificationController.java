package com.infofarm.Users.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    @SendTo("/topic/canal1")
    public String getNotification(String message) throws Exception {
        return message;
    }

    @MessageMapping("/notification")
    public void handleNotification(String notification) {
        // Enviar notificación a un usuario específico
        messagingTemplate.convertAndSendToUser("1", "/queue/notifications", notification);
    }
}
