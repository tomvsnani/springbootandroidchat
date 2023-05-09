package com.example.androidchatmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    List<ChatModel> getAllChatsBetweenUsers(Long user1, Long user2) {
        return chatRepository.findByFromUserModelAndToUserModelOrToUserModelAndFromUserModel(user1, user2, user1, user2);
    }

    ChatModel createChat(ChatModel chatModel) {
        return chatRepository.save(chatModel);
    }

    List<ChatModel> getChatsOnUserId(Long userId){
        return chatRepository.findByUserId(userId);
    }


    Optional<ChatModel> findById(Long id){
        return chatRepository.findById(id);
    }

//    List<ChatModel> getLatestMessageForCurrentUser(Long userId) {
//
//        return chatRepository.findLatestMessagesOfCurrentUser(userId);
//    }
}
