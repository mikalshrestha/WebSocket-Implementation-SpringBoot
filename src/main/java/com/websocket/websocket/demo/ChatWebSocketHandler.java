package com.websocket.websocket.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CopyOnWriteArrayList;

@Component // Mark as a Spring-managed bean
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // Add the new session to the active sessions
        session.getAttributes().put("messages", new CopyOnWriteArrayList<String>()); // Initialize messages list in session
        broadcast("A new user has joined the chat!");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        List<String> messages = (List<String>) session.getAttributes().get("messages");

        // Save the message to the session
        messages.add(message.getPayload());

        // Broadcast the message to all sessions
        broadcast(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // Remove the session from active sessions
        broadcast("A user has left the chat.");
    }

    private void broadcast(String message) {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Optionally, add a method to retrieve messages from the session
    public List<String> getMessagesFromSession(WebSocketSession session) {
        return (List<String>) session.getAttributes().get("messages");
    }
}




