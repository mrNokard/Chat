package com.nokard.chat.controller;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatsController {
    private final ChatService chatService;

    @GetMapping
    public Page<ChatResponse> getPagedList(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false, name = "q") String nameLike
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;


        return (nameLike == null)?
                chatService.getChats(PageRequest.of(page, size)):
                chatService.getChats(PageRequest.of(page, size), nameLike);
    }
}
