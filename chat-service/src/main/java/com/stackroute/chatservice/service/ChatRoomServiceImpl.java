package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.ChatRoom;
import com.stackroute.chatservice.Repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    private ChatRoomRepository repository;

//    @Autowired
//    public ChatRoomServiceImpl(ChatRoomRepository repository){
//        this.repository = repository;
//    }
    @Override
    public Optional<String> getChatId(String senderId, String receiverId) {

        return repository
                .findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    UUID generatedChatId = UUID.randomUUID();
                    String chatId = generatedChatId.toString();

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .receiverId(receiverId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(receiverId)
                            .receiverId(senderId)
                            .build();
                    repository.save(senderRecipient);
                    repository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {

//        Optional<List<ChatRoom>> chatRoomList = repository.findAll();
        return repository.findAll();
    }
}
