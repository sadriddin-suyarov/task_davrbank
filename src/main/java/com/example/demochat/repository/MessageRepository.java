package com.example.demochat.repository;

import com.example.demochat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "select id, chat_id, author_id, text, created_at from message " +
                        "where chat_id = :id " +
                        "order by created_at", nativeQuery = true)
    List<Message> findAllByChatId(Integer id);
}
