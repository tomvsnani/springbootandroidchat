package com.example.androidusermodule;

import com.example.androidusermodule.Models.ChatModel;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

class SocketMessageType {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String type;

    String message;
}

@Component
public class MyHandler extends TextWebSocketHandler {

    @Autowired

    UserRepository userRepository;


    @Autowired

    OpenFeign openFeign;

    HashMap<Long, WebSocketSession> sessionWithUserDetails = new HashMap<>();


    Logger logger = LoggerFactory.getLogger(MyHandler.class);


    @PostConstruct
    public void notifyClientsOnStart() {


    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        logger.debug("message received" + message.toString());


    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        logger.debug("connection closed  " + status);

        if (status.getCode() == CloseStatus.GOING_AWAY.getCode())
            sessionWithUserDetails.forEach((key, value) -> {
                try {
                    value.close(status);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        if (status.getCode() == CloseStatus.NO_CLOSE_FRAME.getCode())
            onClientConnectionClosed(session);
    }

    private void onClientConnectionClosed(WebSocketSession session) {
        sessionWithUserDetails.entrySet().removeIf(e -> e.getValue().getId().equals(session.getId()));

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {


    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        Gson gson = new Gson();

        // on connection established save user d of that device

        if (message.getPayload().contains("userid")) {

            Long id = Long.valueOf(gson.fromJson(message.getPayload(), SocketMessageType.class).getMessage());

            sessionWithUserDetails.put(
                    id, session);


        }

        // message received acknowledgement  to user -> from user

        else if (message.getPayload().contains("onreceived")) {

            Long id = Long.valueOf(gson.fromJson(message.getPayload(), SocketMessageType.class).getMessage());


            ChatModel chat = openFeign.getChatsByMessageId(id);

            //received

            chat.setMessageStatus(2);

            openFeign.createChat(chat);

            String serializedModel = new Gson().toJson(chat);

            sessionWithUserDetails.get(chat.getFromUserModel()).sendMessage(new TextMessage(serializedModel));



        } else {

            ChatModel chatModelFromGson = gson.fromJson(message.getPayload(), ChatModel.class);


            ChatModel chatModelFromDb = openFeign.createChat(chatModelFromGson);


            if (sessionWithUserDetails.containsKey(chatModelFromDb.getToUserModel())) {

                String serializedModel = new Gson().toJson(chatModelFromDb);


                sessionWithUserDetails.get(chatModelFromDb.getToUserModel()).sendMessage(new TextMessage(serializedModel));

                   }


        }


    }

    public MyHandler() {
        super();
    }

}
