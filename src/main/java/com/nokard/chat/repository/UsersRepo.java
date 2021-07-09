package com.nokard.chat.repository;

import com.nokard.chat.entity.Message;
import com.nokard.chat.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UsersRepo extends CrudRepository<User, Long> {
}
