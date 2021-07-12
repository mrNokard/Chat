package com.nokard.chat.repository;

import com.nokard.chat.dto.attachment.AttachmentResponse;
import com.nokard.chat.entity.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentsRepo extends CrudRepository<Attachment, Long> {
    Page<Attachment> findAll(Pageable of);
    Page<Attachment> findAllByMessage_Id(Long id, Pageable of);
}
