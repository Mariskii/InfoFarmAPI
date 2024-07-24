package com.infofarm.Users.Controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class NotificationController {

    @MessageMapping("/chat")
    @SendTo("/topic/canal1")
    public String getNotification(String message) throws Exception {
        return message;
    }

    @MessageMapping("/private-chat")
    @SendToUser("/topic/private-canal1")
    public String getPrivateNotification(String message, Principal principal) throws Exception {
        return message;
    }
}
