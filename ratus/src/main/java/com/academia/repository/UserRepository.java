package com.academia.repository;

import com.academia.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}

