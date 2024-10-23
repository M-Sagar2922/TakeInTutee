package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    public Optional<String> getChatId(String senderId, String receiverId);
    public List<ChatRoom> getAllChatRooms();
}
