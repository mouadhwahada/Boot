package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.ChatMessage;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findBySenderIdAndRecipientId(Long idSender, Long idReciever);
}
