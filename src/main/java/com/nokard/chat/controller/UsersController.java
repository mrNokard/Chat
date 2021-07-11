package com.nokard.chat.controller;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.dto.user.CreateUserRequest;
import com.nokard.chat.dto.user.DeleteUserResponse;
import com.nokard.chat.dto.user.UpdateUserRequest;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    @GetMapping
    public Page<UserResponse> getPagedList(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false, name = "q") String loginLike
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;
        return (loginLike == null)?
                userService.getUsers(PageRequest.of(page, size)):
                userService.getUsers(PageRequest.of(page, size), loginLike);

    }

    @GetMapping("id{id:\\d+}" )
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("id{id:\\d+}/chats" )
    public Set<ChatResponse> getChats(@PathVariable Long id){
        return userService.getUserChats(id);
    }

    @PostMapping
    public UserResponse putUser(@RequestBody CreateUserRequest request){
        return userService.create(request);
    }

    @DeleteMapping("id{id:\\d+}")
    public DeleteUserResponse deleteUser(@PathVariable Long id){
        return userService.deleteById(id);
    }

    @PutMapping("id{id:\\d+}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        request.setId(id);
        return userService.updateUser(request);
    }
}



