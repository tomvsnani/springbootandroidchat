package com.example.androidusermodule;

import com.example.androidchatmodule.ChatModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8001",name ="chat" )
public interface OpenFeign {

    @GetMapping("/chats/getChats/{user1}/{user2}")
    List<ChatModel> getChats(@PathVariable Long user1, @PathVariable Long user2);

    @PostMapping("/chats/createChat")
    ChatModel createChat(@RequestBody ChatModel chatModel);
}
