package com.example.androidchatmodule;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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

@Entity
public class ChatModel {

    @Id
    Long id;

    public ChatModel(Long id, String message, Long timeStamp, Long fromUserModel, Long toUserModel, Boolean isForwarded, int messageStatus, int messagetype, Byte[] valueArray) {
        this.id = id;
        this.message = message;
        this.timeStamp = timeStamp;
        this.fromUserModel = fromUserModel;
        this.toUserModel = toUserModel;
        this.isForwarded = isForwarded;
        this.messageStatus = messageStatus;
        this.messagetype = messagetype;
        this.valueArray = valueArray;
    }

    @NotEmpty
    String message;

    Long timeStamp = new Date().getTime();

    @NotNull
    Long fromUserModel;

    @NotNull
    Long toUserModel;

    Boolean isForwarded = false;





    int messageStatus;

    int messagetype;

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

    Byte[] valueArray;


    public ChatModel() {
    }


}
