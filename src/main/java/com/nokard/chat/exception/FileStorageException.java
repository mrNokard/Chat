package com.nokard.chat.exception;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String s, Exception ex) {
        super(s, ex);
    }
}
