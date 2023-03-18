package com.example.androidusermodule.controller;


import com.example.androidchatmodule.ChatModel;
import com.example.androidusermodule.Models.UserModel;
import com.example.androidusermodule.OpenFeign;
import com.example.androidusermodule.UserRepository;
import com.example.androidusermodule.services.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    OpenFeign openFeign;

    @PostMapping("/createUser")
    ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel) {

        UserModel userModel1 = userAccountService.registerUser(userModel);


        return ResponseEntity.ok(userModel1);

    }

    @GetMapping("/getUsers")
    ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> userModelList = userAccountService.getUsers();
        return ResponseEntity.ok(userModelList);
    }

    @PostMapping("/createChat")
    ResponseEntity<ChatModel> createChat(@RequestBody ChatModel chatModel) {
        return ResponseEntity.ok(openFeign.createChat(chatModel));
    }

    @GetMapping("/getChats/{user1}/{user2}")
    ResponseEntity<List<ChatModel>> getChats(@PathVariable Long user1,@PathVariable Long user2){

        return ResponseEntity.ok(openFeign.getChats(user1, user2));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> exceptionHandlingClass(MethodArgumentNotValidException exception) {

        return ResponseEntity.badRequest().body(exception.getFieldErrors().get(0).getField() + " : " + exception.getBindingResult().getFieldErrors().get(0).getRejectedValue());
    }


}
