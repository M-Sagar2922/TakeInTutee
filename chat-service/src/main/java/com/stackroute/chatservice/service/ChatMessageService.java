package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    public ChatMessage saveMessage(ChatMessage chatMessage);
    public List<ChatMessage> findChatMessages(String senderId, String receiverId);

}
