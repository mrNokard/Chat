package com.nokard.chat.repository;

import com.nokard.chat.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo extends PagingAndSortingRepository<User, Long> {
    public Page<User> findByLoginIsLike(String like, Pageable p);
    public User findUserById(Long Id);
}
