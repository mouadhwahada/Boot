package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Dtos.Message;
import tn.spring.pispring.Entities.ChatMessage;
//import tn.spring.pispring.Entities.ChatRoom;

import java.util.List;

public interface ChatInterface {


    List<ChatMessage> getMessagesBySenderAndReceiver(Long idSender, Long idReciever);

    ChatMessage addMessage(Message msg);

    void deleteMessage(Long id);
}
