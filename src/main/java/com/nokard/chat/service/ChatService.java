package com.nokard.chat.service;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.repository.ChatsRepo;
import com.nokard.chat.repository.UsersRepo;
import com.nokard.chat.utils.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatsRepo chatsRepo;
    private final UsersRepo usersRepo;

    public Page<ChatResponse> getChats(Pageable p){
        return chatsRepo.findAll(p).map(ChatResponse::new);
    }
    public Page<ChatResponse> getChats(PageRequest of, String q) {
        return chatsRepo.findByNameContainingIgnoreCaseOrderByNameAsc(q, of).map(ChatResponse::new);
    }
    public Set<ChatResponse> getUserChats(Long id){
        if(id == null) throw new NullPointerException("Parameter 'id' cannot be null");

        return usersRepo.findById(id).orElseThrow(Exceptions.USER_NOT_FOUND)
                .getChats()
                .stream()
                .map(ChatResponse::new)
                .collect(Collectors.toSet());
    }

    public ChatResponse getChat(Long id) {
        if(id == null) throw Exceptions.ID_CANNOT_BE_NULL.get();
        return new ChatResponse(
                chatsRepo.findById(id).orElseThrow(Exceptions.CHAT_NOT_FOUND)
        );
    }
}
