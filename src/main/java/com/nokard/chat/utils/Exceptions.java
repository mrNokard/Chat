package com.nokard.chat.utils;

import com.nokard.chat.exception.DuplicateParameterException;
import com.nokard.chat.exception.NotFoundException;

import java.util.function.Supplier;

public class Exceptions {
    public static final Supplier<NullPointerException> ID_CANNOT_BE_NULL = () -> new NullPointerException("Parameter 'id' cannot be null");
    public static final Supplier<NotFoundException> USER_NOT_FOUND = () -> new NotFoundException("User not found");
    public static final Supplier<DuplicateParameterException> USER_ALREADY_EXISTS = () -> new DuplicateParameterException("User already exists");
    public static final Supplier<NotFoundException> CHAT_NOT_FOUND = () -> new NotFoundException("Chat not found");
    public static final Supplier<DuplicateParameterException> CHAT_ALREADY_EXISTS = () -> new DuplicateParameterException("Chat already exists");
}
