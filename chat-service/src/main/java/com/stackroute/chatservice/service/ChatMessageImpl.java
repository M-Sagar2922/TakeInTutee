package com.stackroute.chatservice.service;

import com.stackroute.chatservice.exception.ResourceNotFoundException;
import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ChatMessageImpl implements ChatMessageService {

    private  static Logger log = Logger.getLogger("ChatMessageImplService");

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private ChatRoomService chatRoomService;

//    @Autowired
//    public ChatMessageImpl(ChatMessageRepository repository, ChatRoomService chatRoomService){
//        this.repository = repository;
//        this.chatRoomService =chatRoomService;
//    }

    @Override
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        repository.save(chatMessage);
        return chatMessage;
    }


    @Override
    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
        if (chatRoomService.getChatId(senderId,receiverId).isPresent()){

            String chatId = chatRoomService.getChatId(senderId,receiverId).get();
            log.warning("Chat id is:"+chatId);
            return repository.findByChatId(chatId);
        }
        else {
            return new ArrayList<>();
        }
    }

}

