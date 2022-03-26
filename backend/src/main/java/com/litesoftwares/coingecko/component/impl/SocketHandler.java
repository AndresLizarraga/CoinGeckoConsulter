package com.litesoftwares.coingecko.component.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.xml.soap.Text;

@Component("subProtocolWebSocketHandler")
public class SocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection Stablished");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        JSONObject jsonObject = new JSONObject(payload);
        session.sendMessage(new TextMessage("Hi " + jsonObject.get("name") + " how may we help you?"));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("Connection closed....");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
