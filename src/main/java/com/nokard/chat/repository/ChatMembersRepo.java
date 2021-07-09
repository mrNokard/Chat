package com.nokard.chat.repository;

import com.nokard.chat.entity.ChatMember;
import org.springframework.data.repository.CrudRepository;

public interface ChatMembersRepo extends CrudRepository<ChatMember, Long> {
}
