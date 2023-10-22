package com.example.telegrambottesttask.repository;

import com.example.telegrambottesttask.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


}
