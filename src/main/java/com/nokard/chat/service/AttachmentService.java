package com.nokard.chat.service;

import com.nokard.chat.dto.attachment.AttachmentResponse;
import com.nokard.chat.dto.attachment.DeleteAttachmentResponse;
import com.nokard.chat.entity.Attachment;
import com.nokard.chat.entity.Message;
import com.nokard.chat.exception.FileStorageException;
import com.nokard.chat.exception.MyFileNotFoundException;
import com.nokard.chat.repository.AttachmentsRepo;
import com.nokard.chat.repository.MessagesRepo;
import com.nokard.chat.utils.Exceptions;
import liquibase.logging.Logger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@Log4j2
@Service
public class AttachmentService {
    private final Path pathToUploadFolder = Paths.get("./files").toAbsolutePath();
    private final AttachmentsRepo attachmentsRepo;
    private final MessagesRepo messagesRepo;

    @Autowired
    public AttachmentService(
            AttachmentsRepo attachmentsRepo,
            MessagesRepo messagesRepo
    ) {
        this.attachmentsRepo = attachmentsRepo;
        this.messagesRepo = messagesRepo;

        try {
            Files.createDirectories(this.pathToUploadFolder);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void boundAttachments(Set<Long> attachmentIds, Message msg){
        //TODO: boundAttachment
        Iterable<Attachment> arr = attachmentsRepo.findAllById(attachmentIds);
        for(Attachment i:arr){
            if(i.getMessage() == null) i.setMessage(msg);
        }
        attachmentsRepo.saveAll(arr);
    }

    public Page<AttachmentResponse> getAttachments(Pageable of) {
        return attachmentsRepo.findAll(of).map(AttachmentResponse::new);
    }
    public Page<AttachmentResponse> getAttachments(Long MessageId, Pageable of) {
        return attachmentsRepo.findAllByMessage_Id(MessageId, of).map(AttachmentResponse::new);
    }

    public ResponseEntity<Resource> downloadFile(Long id) {
        Attachment a = attachmentsRepo.findById(id)
                .orElseThrow(Exceptions.NotFound(Exceptions.Objects.ATTACHMENT));

        // Load file as Resource
        Resource resource = loadFileAsResource(String.format("%d", a.getId()));
        // Try to determine file's content type
        String contentType = a.getType();
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + a.getFilename() + "\"")
                .body(resource);
    }
    public AttachmentResponse uploadFile(MultipartFile file, Long messageId){
        Message message = (messageId == null)? null: messagesRepo.findById(messageId)
                .orElseThrow(Exceptions.NotFound(Exceptions.Objects.MESSAGE));

        Attachment a = new Attachment();
        a.setFilename(file.getOriginalFilename());
        a.setType(URLConnection.guessContentTypeFromName(file.getOriginalFilename()));
        a.setMessage(message);
        a = attachmentsRepo.save(a);

        // Normalize file name
        String fileName = StringUtils.cleanPath(String.format("%d", a.getId()));
        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.pathToUploadFolder.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return new AttachmentResponse(a);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private Resource loadFileAsResource(String filename) {
        try {
            Path filePath = this.pathToUploadFolder.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + filename, ex);
        }
    }

    public DeleteAttachmentResponse delete(Long id) {
        if(id == null) throw Exceptions.ParameterCannotBeNull(Exceptions.Parameters.ID).get();
        Attachment a = attachmentsRepo.findById(id)
                .orElseThrow(Exceptions.NotFound(Exceptions.Objects.ATTACHMENT));

        Path filePath = this.pathToUploadFolder.resolve(a.getFilename()).normalize();
        try {
            Files.delete(filePath);
        }catch (IOException ex){
            log.error("Could not delete file: "+filePath.toString());
        }
        attachmentsRepo.deleteById(id);
        return new DeleteAttachmentResponse(a.getFilename());
    }
//    public AttachmentResponse getAttachment(Long id) {
//        return new AttachmentResponse(attachmentsRepo
//                .findById(id)
//                .orElseThrow( Exceptions.NotFound(Exceptions.Objects.ATTACHMENT))
//        );
//    }
}
