package com.example.androidchatmodule;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/getChats/{user1}/{user2}")
    ResponseEntity<List<ChatModel>> getAllChatsBetweenUsers(@PathVariable Long user1, @PathVariable Long user2) {
        List<ChatModel> chatModelList = chatService.getAllChatsBetweenUsers(user1, user2);
        return ResponseEntity.ok(chatModelList);
    }

    @PostMapping("/createChat")
    ResponseEntity<ChatModel> createChat(@Valid @RequestBody ChatModel chatModel){
        ChatModel chatModel1=chatService.createChat(chatModel);
        return ResponseEntity.ok(chatModel1);
    }




    @GetMapping("/chatsonuserid/{userId}")
    ResponseEntity<List<ChatModel>> getLatestChatsByUserIdWithOthers(@PathVariable Long userId){

        return ResponseEntity.ok(chatService.getChatsOnUserId(userId));
    }

    @GetMapping("/chatbyid/{id}")

    ResponseEntity<ChatModel> getChatById(@PathVariable Long id){
        if(chatService.findById(id).isPresent())
        return ResponseEntity.ok(chatService.findById(id).get());

        else
          return   ResponseEntity.ok(null);
    }
}
