package com.example.androidusermodule.services;

import com.example.androidusermodule.Models.UserModel;
import com.example.androidusermodule.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    UserRepository userRepository;

    public UserModel registerUser(UserModel userModel) {

        return userRepository.save(userModel);

    }

    public List<UserModel>  getUsers(){
        return userRepository.findAll();
    }
}
