package com.nokard.chat.service;

import com.nokard.chat.dto.message.*;
import com.nokard.chat.dto.user.UserResponse;
import com.nokard.chat.entity.ChatMember;
import com.nokard.chat.entity.Message;
import com.nokard.chat.enums.Roles;
import com.nokard.chat.repository.AttachmentsRepo;
import com.nokard.chat.repository.ChatMembersRepo;
import com.nokard.chat.repository.MessagesRepo;
import com.nokard.chat.utils.DateUtils;
import com.nokard.chat.utils.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final AttachmentService attachmentService;
    private final ChatMembersRepo chatMembersRepo;
    private final AttachmentsRepo attachmentsRepo;
    private final MessagesRepo messagesRepo;

    public Page<MessageResponse> getMessages(Long idChat, Pageable pageable){
        return messagesRepo.findByChat_IdOrderByIdAsc(idChat, pageable).map(MessageResponse::new);
    }

    @Transactional
    public MessageResponse postMessage(PostMessageRequest request){
        this.checkMessage(request);
        if(request.getIdChat() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.CHAT_ID).get();
        if((request.getContent() == null || request.getContent().trim().equals("")) &&
                (request.getAttachments() == null || request.getAttachments().size() == 0))
            throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.CONTENT).get();
        //TODO:Security
        ChatMember profile = chatMembersRepo
                .findById_ChatIdAndId_UserId(request.getIdChat(), request.getIdAuthor())
                .orElseThrow(() -> new SecurityException("Current user is not a member."));

        if(request.getContent() == null) request.setContent("");
        Message msg = new Message(profile);
        msg.setContent(request.getContent());
        msg = messagesRepo.save(msg);
        attachmentService.boundAttachments(request.getAttachments(), msg);
        return new MessageResponse(msg);
    }


    @Transactional
    public MessageResponse updateMessage(EditMessageRequest request) {
        checkMessage(request);
        Message msg = messagesRepo.findById(request.getId()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.MESSAGE));
        ChatMember profile = chatMembersRepo
                .findById_ChatIdAndId_UserId(msg.getChat().getId(), request.getIdAuthor())
                .orElseThrow(() -> new SecurityException("Current user is not a member."));
        if(!msg.getAuthor().getId().equals(request.getId()) && profile.getRole() == Roles.USER)
            throw new SecurityException("You don't have permission to do that");
        if(request.getContent() != null){
            msg.setContent(request.getContent());
            msg.editMessage();
        }
        return new MessageResponse(messagesRepo.save(msg));

    }

    @Transactional
    public DeleteMessageResponse deleteMessage(Long id, Long userId) {
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();
        if(userId == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.USER_ID).get();
        Message msg = messagesRepo.findById(id).orElseThrow(Exceptions.NotFound(Exceptions.Objects.MESSAGE));
        ChatMember profile = chatMembersRepo
                .findById_ChatIdAndId_UserId(msg.getChat().getId(), userId)
                .orElseThrow(() -> new SecurityException("Current user is not a member."));
        if(!msg.getAuthor().getId().equals(userId) && profile.getRole() == Roles.USER)
            throw new SecurityException("You don't have permission to do that");

        DeleteMessageResponse response = new DeleteMessageResponse(msg.getContent());
        messagesRepo.deleteById(id);
        return response;
    }

    private void checkMessage(BaseMessageRequest request){
        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
        if(request.getIdAuthor() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.AUTHOR_ID).get();
    }
}
