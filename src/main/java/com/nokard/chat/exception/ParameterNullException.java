package com.nokard.chat.exception;

public class ParameterNullException extends NullPointerException{
    public ParameterNullException(String paramName){
        super(String.format("Parameter '%s' cannot be null", paramName));
    }
}
