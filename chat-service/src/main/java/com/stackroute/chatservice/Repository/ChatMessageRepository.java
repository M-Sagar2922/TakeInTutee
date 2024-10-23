package com.stackroute.chatservice.Repository;

import com.stackroute.chatservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {
    long countBySenderIdAndReceiverId(String senderId, String receiverId);
    List<ChatMessage> findByChatId(String chatId);
}
