package com.nokard.chat.repository;

import com.nokard.chat.entity.Chat;
import com.nokard.chat.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatsRepo extends JpaRepository<Chat, Long> {
    Page<Chat> findByNameContainingIgnoreCaseOrderByNameAsc(String like, Pageable p);
    Optional<Chat> findById(Long Id);
    Optional<Chat> findByName(String login);
}
