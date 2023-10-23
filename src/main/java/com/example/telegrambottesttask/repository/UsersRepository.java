package com.example.telegrambottesttask.repository;

import com.example.telegrambottesttask.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


}
