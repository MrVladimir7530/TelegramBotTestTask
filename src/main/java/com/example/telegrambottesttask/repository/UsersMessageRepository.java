package com.example.telegrambottesttask.repository;

import com.example.telegrambottesttask.model.UsersMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMessageRepository extends JpaRepository<UsersMessage, Long> {
}
