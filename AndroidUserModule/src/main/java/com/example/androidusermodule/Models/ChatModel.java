package com.example.androidusermodule.Models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Date;


enum MessageStatus {
    NotSENT(2),
    SENT(0),
    RECEIVED(1);

    final int value;

    MessageStatus(int i) {
        this.value = i;
    }
}


enum MessageType {

    AUDIO(0),

    IMAGE(1),

    TEXT(2);


    final int value;

    MessageType(int i) {
        this.value = i;
    }
}


public class ChatModel {


    Long id;

    @Override
    public String toString() {
        return "ChatModel{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                ", fromUserModel=" + fromUserModel +
                ", toUserModel=" + toUserModel +
                ", isForwarded=" + isForwarded +
                ", messageStatus=" + messageStatus +
                ", messagetype=" + messagetype +
                ", valueArray=" + Arrays.toString(valueArray) +
                '}';
    }



    @NotEmpty
    String message;

    Long timeStamp = new Date().getTime();

    @NotNull
    Long fromUserModel;

    @NotNull
    Long toUserModel;

    Boolean isForwarded = false;

    public Boolean getForwarded() {
        return isForwarded;
    }

    public void setForwarded(Boolean forwarded) {
        isForwarded = forwarded;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public int getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(int messagetype) {
        this.messagetype = messagetype;
    }

    public Byte[] getValueArray() {
        return valueArray;
    }

    public void setValueArray(Byte[] valueArray) {
        this.valueArray = valueArray;
    }

    int messageStatus;

    int messagetype;

    Byte[] valueArray;


    public ChatModel() {
    }

    public ChatModel(String message, Long timeStamp, Long fromUser, Long toUser) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.fromUserModel = fromUser;
        this.toUserModel = toUser;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getFromUserModel() {
        return fromUserModel;
    }

    public void setFromUserModel(Long fromUserModel) {
        this.fromUserModel = fromUserModel;
    }

    public Long getToUserModel() {
        return toUserModel;
    }

    public void setToUserModel(Long toUserModel) {
        this.toUserModel = toUserModel;
    }


}
