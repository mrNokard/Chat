package com.nokard.chat.repository;

import com.nokard.chat.entity.ChatMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatMembersRepo extends JpaRepository<ChatMember, Long> {
    Optional<ChatMember> findById_ChatIdAndId_UserId(Long chatId, Long userId);
    Page<ChatMember> findAllById_ChatId(Long chatId, Pageable of);
}
