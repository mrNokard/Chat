package com.nokard.chat.utils;

import com.nokard.chat.exception.DuplicateParameterException;
import com.nokard.chat.exception.NotFoundException;
import com.nokard.chat.exception.ParameterNullException;

import java.util.function.Supplier;

public class Exceptions {
    public static final Supplier<NullPointerException> ID_CANNOT_BE_NULL = () -> new ParameterNullException("id");
    public static final Supplier<NullPointerException> NAME_CANNOT_BE_NULL = () -> new ParameterNullException("name");
    public static final Supplier<NullPointerException> LOGIN_CANNOT_BE_NULL = () -> new ParameterNullException("login");
    public static final Supplier<NullPointerException> REQUEST_CANNOT_BE_NULL = () -> new NullPointerException("Request cannot be null");
    public static final Supplier<NotFoundException> USER_NOT_FOUND = () -> new NotFoundException("User not found");
    public static final Supplier<NotFoundException> CHAT_NOT_FOUND = () -> new NotFoundException("Chat not found");
    public static final Supplier<DuplicateParameterException> USER_ALREADY_EXISTS = () -> new DuplicateParameterException("User already exists");
    public static final Supplier<DuplicateParameterException> CHAT_MEMBER_ALREADY_EXISTS = () -> new DuplicateParameterException("ChatMember already exists");


    public static final Supplier<NullPointerException> MEMBER_ID_CANNOT_BE_NULL = () -> new ParameterNullException("idMember");
    public static final Supplier<NullPointerException> CHAT_ID_CANNOT_BE_NULL = () -> new ParameterNullException("idChat");
}
