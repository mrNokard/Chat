package com.nokard.chat.repository;

import com.nokard.chat.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepo extends JpaRepository<User, Long> {
    Page<User> findByLoginIsLike(String like, Pageable p);
    Optional<User> findById(Long Id);
    Optional<User> findByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);
}
