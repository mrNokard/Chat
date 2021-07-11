package com.nokard.chat.repository;

import com.nokard.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepo extends CrudRepository<Message, Long> {
    Page<Message> findByChat_IdOrderByIdAsc(Long chatId, Pageable p);
}
