package com.nokard.chat.controller;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.dto.message.MessageResponse;
import com.nokard.chat.service.ChatService;
import com.nokard.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatsController {
    private final ChatService chatService;
    private final MessageService messageService;

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

    @GetMapping("/id{id:\\d+}")
    public ChatResponse getChat(@PathVariable Long id){ return chatService.getChat(id); }

    @GetMapping("/id{id:\\d+}/messages")
    public Page<MessageResponse> getChatMessages(
            @PathVariable Long id,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;

        return messageService.getChatMessages(id, PageRequest.of(page, size));
    }
}
