package com.nokard.chat.repository;

import com.nokard.chat.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatsRepo extends CrudRepository<Chat, Long> {
}
