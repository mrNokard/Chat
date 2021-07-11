package com.nokard.chat.service;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.repository.ChatsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatsRepo chatsRepo;

    public Page<ChatResponse> getChats(Pageable p){
        return chatsRepo.findAll(p).map(ChatResponse::new);
    }
    public Page<ChatResponse> getChats(PageRequest of, String q) {
        return chatsRepo.findByNameContainingIgnoreCaseOrderByNameAsc(q, of).map(ChatResponse::new);
    }
}
