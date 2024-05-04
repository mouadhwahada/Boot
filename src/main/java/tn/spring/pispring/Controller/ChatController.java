package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Dtos.Message;
import tn.spring.pispring.Interfaces.ChatInterface;
import tn.spring.pispring.Entities.ChatMessage;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
@AllArgsConstructor
public class ChatController {

    private ChatInterface chatService;

    private SimpMessagingTemplate messagingTemplate;
    @GetMapping("/messages/{senderId}/{receiverId}")
    public List<ChatMessage> getMessagesBySenderAndReceiver(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return chatService.getMessagesBySenderAndReceiver(senderId, receiverId);
    }

    @DeleteMapping("/deleteMessage/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        chatService.deleteMessage(messageId);
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message msg) {
        System.out.println("Sending msg with websoket");
        ChatMessage chatMessage = chatService.addMessage(msg);

        messagingTemplate.convertAndSend(
                "/topic/messages/" + chatMessage.getRecipient().getId(),
                chatMessage
        );
        messagingTemplate.convertAndSend(
                "/topic/messages/" + chatMessage.getSender().getId(),
                chatMessage
        );
    }


}
