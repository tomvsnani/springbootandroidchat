package com.example.androidchatmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatModel, Long> {

    List<ChatModel> findByFromUserModelAndToUserModelOrToUserModelAndFromUserModel(Long fromUser, Long toUser, Long fromUser1, Long fromUser2);

//    @Query("SELECT c FROM ChatModel c " +
//            "INNER JOIN (SELECT MAX(c.timeStamp) AS timestamp, " +
//            "CASE WHEN c.fromUserModel = :userId THEN c.toUserModel " +
//            "ELSE c.fromUserModel END AS otheruser " +
//            "FROM ChatModel c " +
//            "WHERE c.fromUserModel=:userId OR c.toUserModel=:userId " +
//            "GROUP BY otheruser) AS maxtime " +
//            "ON ((c.fromUserModel=maxtime.otheruser AND c.toUserModel=:userId) " +
//            "OR (c.toUserModel=maxtime.otheruser AND c.fromUserModel=:userId)) " +
//            "AND c.timeStamp=maxtime.timestamp")
//    List<ChatModel> findLatestMessagesOfCurrentUser(@Param("userId") Long user);


    @Query("SELECT c FROM ChatModel c WHERE c.fromUserModel=:id OR c.toUserModel=:id")
    List<ChatModel> findByUserId(Long id);


}
