package com.example.demochat.repository;

import com.example.demochat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    @Query(value = "select count(id) from users")
    int getRowCount();
}
