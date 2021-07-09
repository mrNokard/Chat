package com.nokard.chat.repository;

import com.nokard.chat.entity.Attachment;
import org.springframework.data.repository.CrudRepository;


public interface AttachmentsRepo extends CrudRepository<Attachment, Long> {
}
