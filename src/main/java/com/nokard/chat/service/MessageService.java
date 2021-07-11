package com.nokard.chat.service;

import com.nokard.chat.dto.message.MessageResponse;
import com.nokard.chat.entity.Message;
import com.nokard.chat.repository.MessagesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    public final MessagesRepo messagesRepo;

    public Page<MessageResponse> getChatMessages(Long id, Pageable pageable){
        return messagesRepo.findByChat_IdOrderByIdAsc(id, pageable).map(MessageResponse::new);
    }
}
