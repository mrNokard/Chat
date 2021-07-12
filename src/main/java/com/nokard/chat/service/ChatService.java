package com.nokard.chat.service;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.dto.chat.CreateChatRequest;
import com.nokard.chat.dto.chat.DeleteChatResponse;
import com.nokard.chat.dto.chat.UpdateChatRequest;
import com.nokard.chat.dto.chatmember.AddChatMemberRequest;
import com.nokard.chat.dto.chatmember.ChatMemberNodeResponse;
import com.nokard.chat.dto.chatmember.ChatMemberResponse;
import com.nokard.chat.dto.chatmember.UpdateChatMemberRequest;
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
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();

        return usersRepo.findById(id).orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER))
                .getChats()
                .stream()
                .map(ChatResponse::new)
                .collect(Collectors.toSet());
    }
    public ChatResponse getChat(Long id) {
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();
        return new ChatResponse(
                chatsRepo.findById(id).orElseThrow(Exceptions.NotFound(Exceptions.Objects.CHAT))
        );
    }
    public Page<ChatMemberNodeResponse> getChatMembers(Long id, Pageable of) {
        return chatMembersRepo.findAllById_ChatId(id, of).map(ChatMemberNodeResponse::new);
    }

    @Transactional
    public ChatResponse createChat(CreateChatRequest request) {
        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
        Chat c = new Chat();
        c.setName(Optional.of(request.getName()).orElseThrow(Exceptions.ParameterCannotBeNull(Exceptions.Parameters.NAME)));
        c.setDescription(request.getDescription());

        User owner = usersRepo.
                findById(Optional.of(
                        request.getCreatorId())
                        .orElseThrow(Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID)))
                .orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER));
        Chat result = chatsRepo.save(c);

        ChatMember profile = new ChatMember(result, owner);
        profile.setNotify(true);
        profile.setRole(Roles.OWNER);
        chatMembersRepo.save(profile);

        return new ChatResponse(result);
    }
    @Transactional
    public ChatResponse updateChat(UpdateChatRequest request){
        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
        if(request.getId() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();

        Chat chat = chatsRepo.findById(request.getId()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.CHAT));
        if(request.getName() != null) chat.setName(request.getName());
        if(request.getDescription() != null) chat.setDescription(request.getDescription());

        chat = chatsRepo.save(chat);
        return new ChatResponse(chat);
    }
    @Transactional
    public DeleteChatResponse deleteChat(Long id) {
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();
        String name = chatsRepo.findById(id).orElseThrow(Exceptions.NotFound(Exceptions.Objects.CHAT)).getName();

        chatsRepo.deleteById(id);
        DeleteChatResponse r = new DeleteChatResponse();
        r.setName(name);
        return r;
    }

    @Transactional
    public ChatMemberResponse addMember(AddChatMemberRequest request){
        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
        if(request.getIdChat() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.CHAT_ID).get();
        if(request.getIdMember() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.MEMBER_ID).get();
        User u = usersRepo.findById(request.getIdMember()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER));
        Chat c = chatsRepo.findById(request.getIdChat()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.CHAT));
        if(chatMembersRepo.findById_ChatIdAndId_UserId(request.getIdChat(), request.getIdMember()).isPresent())
            throw Exceptions.AlreadyExists(Exceptions.Objects.CHAT_MEMBER).get();

        if(request.getRole() == null) request.setRole(Roles.USER);
        if(request.getNotify() == null) request.setNotify(true);
        ChatMember profile = new ChatMember(c, u);
        profile.setRole(request.getRole());
        profile.setNotify(request.getNotify());
        return new ChatMemberResponse(chatMembersRepo.save(profile));
    }

//    @Transactional
//    public ChatMemberResponse updateMember(UpdateChatMemberRequest request){
//        if(request == null) throw Exceptions.CannotBeNull(Exceptions.Objects.REQUEST).get();
//        if(request.getIdChat() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.CHAT_ID).get();
//        if(request.getIdMember() == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.MEMBER_ID).get();
//        User u = usersRepo.findById(request.getIdMember()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.USER));
//        Chat c = chatsRepo.findById(request.getIdChat()).orElseThrow(Exceptions.NotFound(Exceptions.Objects.CHAT));
//        if(!chatMembersRepo.findById_ChatIdAndId_UserId(request.getIdChat(), request.getIdMember()).isPresent())
//            throw Exceptions.AlreadyExists(Exceptions.Objects.CHAT_MEMBER).get();
//
//        if(request.getRole() == null) request.setRole(Roles.USER);
//        if(request.getNotify() == null) request.setNotify(true);
//        ChatMember profile = new ChatMember(c, u);
//        profile.setRole(request.getRole());
//        profile.setNotify(request.getNotify());
//        return new ChatMemberResponse(chatMembersRepo.save(profile));
//    }
}
