package com.example.demochat.repository;

import com.example.demochat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    boolean existsByName(String name);

    @Query(value = "select count(id) from chat", nativeQuery = true)
    int getRowCount();

    @Query(value = "select * " +
            "from chat c " +
            "   join chat_users cu on c.id = cu.chat_id " +
            "   join users u on u.id = cu.users_id " +
            "where u.id = :userId order by c.created_at", nativeQuery = true)
    List<Chat> getAllByUserIdOrderedByCreatedAt(Integer userId);
}
