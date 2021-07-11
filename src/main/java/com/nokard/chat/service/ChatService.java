package com.nokard.chat.service;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.dto.chat.CreateChatRequest;
import com.nokard.chat.dto.chat.DeleteChatResponse;
import com.nokard.chat.dto.chat.UpdateChatRequest;
import com.nokard.chat.dto.chatmember.ChatMemberNodeResponse;
import com.nokard.chat.entity.Chat;
import com.nokard.chat.entity.ChatMember;
import com.nokard.chat.entity.User;
import com.nokard.chat.enums.Roles;
import com.nokard.chat.repository.ChatMembersRepo;
import com.nokard.chat.repository.ChatsRepo;
import com.nokard.chat.repository.UsersRepo;
import com.nokard.chat.utils.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMembersRepo chatMembersRepo;
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
    public Page<ChatMemberNodeResponse> getChatMembers(Long id, Pageable of) {
        return chatMembersRepo.findAllById_ChatId(id, of).map(ChatMemberNodeResponse::new);
    }

    @Transactional
    public ChatResponse createChat(CreateChatRequest request) {
        if(request == null) throw Exceptions.REQUEST_CANNOT_BE_NULL.get();
        Chat c = new Chat();
        c.setName(Optional.of(request.getName()).orElseThrow(Exceptions.NAME_CANNOT_BE_NULL));
        c.setDescription(request.getDescription());

        User owner = usersRepo.
                findById(Optional.of(
                        request.getCreatorId())
                        .orElseThrow(Exceptions.ID_CANNOT_BE_NULL))
                .orElseThrow(Exceptions.USER_NOT_FOUND);
        Chat result = chatsRepo.save(c);

        ChatMember profile = new ChatMember(result, owner);
        profile.setNotify(true);
        profile.setRole(Roles.OWNER);
        chatMembersRepo.save(profile);

        return new ChatResponse(result);
    }
    @Transactional
    public ChatResponse updateChat(UpdateChatRequest request){
        if(request == null) throw Exceptions.REQUEST_CANNOT_BE_NULL.get();
        if(request.getId() == null) throw Exceptions.ID_CANNOT_BE_NULL.get();

        Chat chat = chatsRepo.findById(request.getId()).orElseThrow(Exceptions.CHAT_NOT_FOUND);
        if(request.getName() != null) chat.setName(request.getName());
        if(request.getDescription() != null) chat.setDescription(request.getDescription());

        chat = chatsRepo.save(chat);
        return new ChatResponse(chat);
    }
    @Transactional
    public DeleteChatResponse deleteChat(Long id) {
        if(id == null) throw Exceptions.ID_CANNOT_BE_NULL.get();
        String name = chatsRepo.findById(id).orElseThrow(Exceptions.CHAT_NOT_FOUND).getName();

        chatsRepo.deleteById(id);
        DeleteChatResponse r = new DeleteChatResponse();
        r.setName(name);
        return r;
    }
}
