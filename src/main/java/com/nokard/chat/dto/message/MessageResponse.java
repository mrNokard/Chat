package com.nokard.chat.dto.message;

import com.nokard.chat.entity.Attachment;
import com.nokard.chat.entity.Message;
import com.nokard.chat.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
    private Long id;
    private String content;
    private Set<Long> attachmentIds;
    private Long sent;
    private Long edited;

    public MessageResponse(Message msg){
        this.id = msg.getId();
        this.content = msg.getContent();
        this.attachmentIds = msg.getAttachments().stream().map(Attachment::getId).collect(Collectors.toSet());
        this.sent = DateUtils.toUnix(msg.getSent());
        this.edited = DateUtils.toUnix(msg.getEdited());
    }

}
