package com.example.telegrambottesttask.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users_message")
public class UsersMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersMessageId;
    private String sentMessage;
    private String receivedMessage;
    @ManyToOne
    @JoinColumn(name = "users_chat_id")
    private Users users;
}
