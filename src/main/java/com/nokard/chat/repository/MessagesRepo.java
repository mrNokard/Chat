package com.nokard.chat.repository;

import com.nokard.chat.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepo extends CrudRepository<Message, Long> {
}
