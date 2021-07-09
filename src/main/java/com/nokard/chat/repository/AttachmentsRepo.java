package com.nokard.chat.repository;

import com.nokard.chat.entity.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentsRepo extends CrudRepository<Attachment, Long> {
}
