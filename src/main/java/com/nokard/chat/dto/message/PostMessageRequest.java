package com.nokard.chat.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostMessageRequest extends BaseMessageRequest {
    private Long idChat;
    protected Set<Long> attachments;
    public PostMessageRequest(Long id, Long idAuthor, Long idChat, String content, Set<Long> attachments) {
        super(id, idAuthor, content);
        this.idChat = idChat;
        this.attachments = attachments;

    }
}
