package com.example.telegrambottesttask.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "users")
public class Users {
    @Id
    private Long chatId;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDateTime registeredAt;
    private LocalDateTime lastMessageAt;
}
