package ftn.kts.repository;

import ftn.kts.model.Message;
import ftn.kts.model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiverIdOrSenderId(Long receiverId, Long senderId);



    List<Message> findAllByMessageType(MessageType messageType);

}
