package com.hostbooks.repository;

import com.hostbooks.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {

    public User findByUsername(String username);
}
