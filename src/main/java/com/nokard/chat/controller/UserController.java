package com.nokard.chat.controller;

import com.nokard.chat.dto.user.UserListResponse;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<UserListResponse> getPagedList(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String q
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;
        return (q == null)?
                userService.getUsers(PageRequest.of(page, size)):
                userService.getUsers(PageRequest.of(page, size), q);

    }

    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
}

