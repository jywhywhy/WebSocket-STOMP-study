package com.websocket.ws.tutorial;

import com.websocket.ws.tutorial.dto.ResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {
    private SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;

    public WSService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(String message) {
        ResponseMessage response = new ResponseMessage(message);
        notificationService.sendGlobalNotification();
        System.out.println(1234);
        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(String id, String message) {
        ResponseMessage response = new ResponseMessage(message);
        notificationService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages", response);
    }
}
