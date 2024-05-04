package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Dtos.Message;
import tn.spring.pispring.Entities.ChatMessage;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.ChatInterface;
import tn.spring.pispring.Repositories.ChatMessageRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService implements ChatInterface {
    private ChatMessageRepo chatMessageRepo;
    private UserRepo userRepo;
    @Override
    public List<ChatMessage> getMessagesBySenderAndReceiver(Long idSender, Long idReciever) {
        return chatMessageRepo.findBySenderIdAndRecipientId(idSender, idReciever);
    }
    @Override
    public ChatMessage addMessage(Message msg) {
        User sender = userRepo.findById(msg.getIdSender()).orElseThrow(() -> new RuntimeException("Sender not found."));
        User recipient = userRepo.findById(msg.getIdRecipient()).orElseThrow(() -> new RuntimeException("Recipient not found."));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(msg.getContent());
        chatMessage.setSentAt(new Date(System.currentTimeMillis()));
        chatMessage.setSender(sender);
        chatMessage.setRecipient(recipient);

       return chatMessageRepo.save(chatMessage);
    }
    @Override
    public void deleteMessage(Long id) {
        chatMessageRepo.deleteById(id);
    }

}
