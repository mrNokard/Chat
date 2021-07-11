package com.nokard.chat.utils;

import com.nokard.chat.exception.DuplicateParameterException;
import com.nokard.chat.exception.NotFoundException;

import java.util.function.Supplier;

public class Exceptions {
    public static final Supplier<NotFoundException> USER_NOT_FOUND = () -> new NotFoundException("User not found");
    public static final Supplier<DuplicateParameterException> USER_ALREADY_EXISTS = () -> new DuplicateParameterException("User already exists");
}