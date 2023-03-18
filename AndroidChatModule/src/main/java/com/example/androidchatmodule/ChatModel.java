package com.example.androidchatmodule;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class ChatModel {

    @Id
    @GeneratedValue
    Long messageId;

    String message;

    LocalDateTime timeStamp = LocalDateTime.now();

    Long fromUser;

    public ChatModel(){}

    public ChatModel(String message, LocalDateTime timeStamp, Long fromUser, Long toUser) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }

    Long toUser;

}
