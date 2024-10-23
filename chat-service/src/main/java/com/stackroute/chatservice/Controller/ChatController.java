package com.stackroute.chatservice.Controller;

import com.stackroute.chatservice.exception.ResourceNotFoundException;
import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.model.ChatRoom;
import com.stackroute.chatservice.service.ChatMessageService;
import com.stackroute.chatservice.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000/")
@Slf4j
public class ChatController {
    private ChatMessageService chatMessageService;
    private ChatRoomService chatRoomService;
    private SimpMessagingTemplate messagingTemplate;
    private ResponseEntity responseEntity;

    @Autowired
    public ChatController(ChatMessageService chatMessageService, ChatRoomService chatRoomService, SimpMessagingTemplate messagingTemplate){
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){

        String chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getReceiverId()).get();
        chatMessage.setChatId(chatId);

        ChatMessage savedMessage = chatMessageService.saveMessage(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getSenderId(),"/queue/messages", savedMessage);
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<?> findChatMessages (@PathVariable String senderId, @PathVariable String receiverId) throws ResourceNotFoundException {
        try{
            List<ChatMessage> chatMessageList = chatMessageService.findChatMessages(senderId, receiverId);
            responseEntity = new ResponseEntity<>(chatMessageList,HttpStatus.OK);
        } catch (Exception e) {
            log.error("error log:"+e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());

        }
//        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
        return responseEntity;
    }

    @GetMapping("/all/chatrooms")
    public ResponseEntity<?> getAllChatRooms(){
        try{
            List<ChatRoom> chatRoomList = chatRoomService.getAllChatRooms();
            responseEntity = new ResponseEntity<>(chatRoomList,HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: "+e.getMessage());
        }
//        return ResponseEntity.ok(chatRoomService.getAllChatRooms());
        return responseEntity;
    }
}
