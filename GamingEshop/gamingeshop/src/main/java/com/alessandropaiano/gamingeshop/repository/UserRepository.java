package com.alessandropaiano.gamingeshop.repository;

import com.alessandropaiano.gamingeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
