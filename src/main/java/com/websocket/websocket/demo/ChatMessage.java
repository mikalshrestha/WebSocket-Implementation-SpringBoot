//package com.websocket.websocket.demo;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
//public class ChatMessage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String sessionId;
//    private String message;
//
//    // Constructors, getters, and setters
//    public ChatMessage() {
//    }
//
//    public ChatMessage(String sessionId, String message) {
//        this.sessionId = sessionId;
//        this.message = message;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getSessionId() {
//        return sessionId;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//}
//
