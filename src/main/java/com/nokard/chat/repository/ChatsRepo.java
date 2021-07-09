package com.nokard.chat.repository;

import com.nokard.chat.entity.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatsRepo extends CrudRepository<Chat, Long> {
}
