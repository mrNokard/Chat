package com.nokard.chat.dto.attachment;

import com.nokard.chat.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse {
    private Long id;
    private String filename;
    private String type;
    private Long messageId;

    public AttachmentResponse(Attachment from){
        this.id = from.getId();
        this.filename = from.getFilename();
        this.type = from.getType();
        this.messageId = from.getMessage() == null? null: from.getMessage().getId();
    }
}
