package com.nokard.chat.repository;

import com.nokard.chat.entity.ChatMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMembersRepo extends CrudRepository<ChatMember, Long> {
}
