package com.nokard.chat.service;

import com.nokard.chat.dto.attachment.BoundResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    public BoundResponse boundAttachments(Set<Long> attachmentIds, Long MessageId){
        //TODO: boundAttachment
        return null;
    }
}
