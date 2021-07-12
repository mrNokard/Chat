package com.nokard.chat.controller;

import com.nokard.chat.dto.attachment.AttachmentResponse;
import com.nokard.chat.dto.attachment.DeleteAttachmentResponse;
import com.nokard.chat.entity.Attachment;
import com.nokard.chat.service.AttachmentService;
import com.nokard.chat.utils.Exceptions;
import liquibase.logging.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentsController {
    private final AttachmentService attachmentService;

    @GetMapping
    public Page<AttachmentResponse> getAttachments(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;
        return attachmentService.getAttachments(PageRequest.of(page, size));
    }
    @GetMapping("/messages/id{messageId:\\d+}")
    public Page<AttachmentResponse> getMessageAttachments(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @PathVariable Long messageId
    ){
        if(size == null) size = 10;
        if(page == null) page = 0;
        return attachmentService.getAttachments(messageId, PageRequest.of(page, size));
    }

    @PostMapping(value={"", "/messages/id{messageId:\\d+}"})
    public AttachmentResponse uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable(required = false) Long messageId
    ){
        return attachmentService.uploadFile(file, messageId);
    }
    @GetMapping("/id{id:\\d+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){
        return attachmentService.downloadFile(id);
    }
    @DeleteMapping("/id{id:\\d+}")
    public DeleteAttachmentResponse removeFile(@PathVariable Long id){
        return attachmentService.delete(id);
    }
}
