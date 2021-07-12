package com.nokard.chat.controller;

import com.nokard.chat.dto.message.DeleteMessageResponse;
import com.nokard.chat.dto.message.EditMessageRequest;
import com.nokard.chat.dto.message.MessageResponse;
import com.nokard.chat.dto.message.PostMessageRequest;
import com.nokard.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessagesController {
    private final MessageService messageService;

    @GetMapping("/chats/id{chatId:\\d+}")
    public Page<MessageResponse> getMessages(
            @PathVariable Long chatId,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;

        return messageService.getMessages(chatId, PageRequest.of(page, size));
    }

    @PostMapping("/chats/id{chatId:\\d+}")
    public MessageResponse postMessage(
            @PathVariable Long chatId,
            @RequestParam Long userId,
            @RequestBody PostMessageRequest request
    ){
        request.setIdChat(chatId);
        request.setIdAuthor(userId);
        return messageService.postMessage(request);
    }

    @PutMapping("id{id:\\d+}")
    public MessageResponse putMessage(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestBody EditMessageRequest request
    ){
        request.setIdAuthor(userId);
        request.setId(id);
        return messageService.updateMessage(request);
    }

    @DeleteMapping("id{id:\\d+}")
    public DeleteMessageResponse deleteMessage(
            @PathVariable Long id,
            @RequestParam Long userId
    ){
        //TODO:Security
        return messageService.deleteMessage(id, userId);
    }
}
