package com.nokard.chat.service;

import com.nokard.chat.dto.user.UserListResponse;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepo usersRepo;

    public Page<UserListResponse> getUsers(Pageable p){
        return usersRepo.findAll(p).map(UserListResponse::new);
    }

    public Page<UserListResponse> getUsers(PageRequest of, String q) {
        return usersRepo.findByLoginIsLike(q, of).map(UserListResponse::new);
    }


    public UserResponse getUser(Long id){
        if(id == null) throw new NullPointerException("Parameter 'id' cannot be null");

        return new UserResponse(usersRepo.findUserById(id));
    }
}
