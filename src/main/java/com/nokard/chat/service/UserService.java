package com.nokard.chat.service;

import com.nokard.chat.dto.user.CreateUserRequest;
import com.nokard.chat.dto.user.DeleteUserResponse;
import com.nokard.chat.dto.user.UpdateUserRequest;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.entity.User;
import com.nokard.chat.repository.UsersRepo;
import com.nokard.chat.utils.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepo usersRepo;

    public Page<UserResponse> getUsers(Pageable p){
        return usersRepo.findAll(p).map(UserResponse::new);
    }
    public Page<UserResponse> getUsers(PageRequest of, String q) {
        return usersRepo.findByLoginContainingIgnoreCaseOrderByLoginAsc(q, of).map(UserResponse::new);
    }


    public UserResponse getUser(Long id) {
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();

        return new UserResponse(usersRepo.findById(id).orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER)));
    }
    @Transactional
    public UserResponse create(CreateUserRequest request) {
        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
        if(request.getLogin() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.LOGIN).get();
        if(usersRepo.findByLogin(request.getLogin()).isPresent()) throw Exceptions.CannotBeNull(Exceptions.Objects.USER).get();

        User u = new User();
        u.setLogin(request.getLogin());
        u.setBio(request.getBio());
        return new UserResponse(usersRepo.save(u));
    }

    public DeleteUserResponse deleteById(Long id) {
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();
        String login = usersRepo.findById(id).orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER)).getLogin();
        usersRepo.deleteById(id);
        DeleteUserResponse r = new DeleteUserResponse();
        r.setLogin(login);
        return r;
    }

    @Transactional
    public UserResponse updateUser(UpdateUserRequest request) {
        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
        if(request.getId() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();
        //<1917>
        if (request.getLogin() != null && usersRepo.existsByLoginAndIdNot(request.getLogin(), request.getId()))
            throw Exceptions.CannotBeNull(Exceptions.Objects.USER).get();
        //</1917>
        User user = usersRepo.findById(request.getId()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER));

        if(request.getLogin() != null) user.setLogin(request.getLogin());
        if(request.getBio() != null) user.setBio(request.getBio());

        return new UserResponse(usersRepo.save(user));
    }
}
