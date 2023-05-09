package com.example.androidusermodule.controller;


import com.example.androidusermodule.Models.ChatModel;
import com.example.androidusermodule.Models.UserModel;
import com.example.androidusermodule.OpenFeign;
import com.example.androidusermodule.UserRepository;
import com.example.androidusermodule.services.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

        Optional<UserModel> userModelOptional =userRepository.findByName(userModel.getName());

        if (userModelOptional.isEmpty()) {

            UserModel dbModel = userAccountService.registerUser(userModel);

            return ResponseEntity.ok(dbModel);
        }
        else {

            return ResponseEntity.ok(userModelOptional.get());
        }




    }

    @GetMapping("/getUsers")
    ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> userModelList = userAccountService.getUsers();
        return ResponseEntity.ok(userModelList);
    }

    @PostMapping("/createChat")
    ResponseEntity<ChatModel> createChat(@Valid @RequestBody ChatModel chatModel) {

        System.out.println(chatModel.toString());

        return ResponseEntity.ok(openFeign.createChat(chatModel));
    }

    @GetMapping("/getChats/{user1}/{user2}")
    ResponseEntity<List<ChatModel>> getChats(@PathVariable Long user1, @PathVariable Long user2) {

        return ResponseEntity.ok(openFeign.getChats(user1, user2));
    }


    @GetMapping("/chatsonid/{id}")
    ResponseEntity<List<ChatModel>> getChatsOnId(@PathVariable Long id) {
        return ResponseEntity.ok(openFeign.getChatsonUserId(id));
    }

//    @GetMapping("/getlatestchats/{id}")
//
//    ResponseEntity<List<ChatModel>> getLatestChats(@PathVariable Long id){
//        return  ResponseEntity.ok(openFeign.getLatestChats(id));
//    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<String> upload(@RequestBody MultipartFile file, @RequestParam String hey) {
        return ResponseEntity.ok(file.getSize() + " " + hey);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<FieldError>> exceptionHandlingClass(MethodArgumentNotValidException exception) {

        return ResponseEntity.badRequest().body(exception.getFieldErrors());
    }


}
