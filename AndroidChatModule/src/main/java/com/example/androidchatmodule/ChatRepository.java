package com.example.androidchatmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatModel,Long> {

    List<ChatModel> findByFromUserAndToUserOrToUserAndFromUser(Long fromUser,Long toUser,Long fromUser1,Long fromUser2);

}
