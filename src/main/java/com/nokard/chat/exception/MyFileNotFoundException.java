package com.nokard.chat.exception;

public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String s) {
        super(s);
    }
    public MyFileNotFoundException(String s, Exception ex) {
        super(s, ex);
    }

}
