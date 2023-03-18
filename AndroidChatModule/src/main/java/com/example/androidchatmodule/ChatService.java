package com.example.androidchatmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
   private ChatRepository chatRepository;

    List<ChatModel> getAllChats(Long user1,Long user2){
        return chatRepository.findByFromUserAndToUserOrToUserAndFromUser(user1,user2,user1,user2);
    }

    ChatModel createChat(ChatModel chatModel){
        return  chatRepository.save(chatModel);
    }
}
