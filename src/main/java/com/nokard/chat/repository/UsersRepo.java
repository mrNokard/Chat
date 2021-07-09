package com.nokard.chat.repository;

import com.nokard.chat.entity.Message;
import com.nokard.chat.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsersRepo extends CrudRepository<User, Long> {
}
