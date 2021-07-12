package com.nokard.chat.utils;

import com.nokard.chat.exception.DuplicateParameterException;
import com.nokard.chat.exception.NotFoundException;
import com.nokard.chat.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class Exceptions {
    @Getter
    @AllArgsConstructor
    public enum Parameters{
        ID("id"),
        LOGIN("login"),
        NAME("name"),
        CHAT_ID("chatId"),
        USER_ID("userId"),
        MEMBER_ID("memberId"),
        AUTHOR_ID("authorId"),
        CONTENT("content");
        private String s;
    }
    @Getter
    @AllArgsConstructor
    public enum Objects{
        REQUEST("Request"),
        MESSAGE("Message"),
        CHAT("Chat"),
        CHAT_MEMBER("ChatMember"),
        USER("User");
        private String s;
    }

    public static Supplier<NullPointerException> ParameterCannotBeNull(Parameters s){return Exceptions.ParameterCannotBeNull(s.getS());}
    public static Supplier<NullPointerException> ParameterCannotBeNull(String s){ return () -> new ParameterNullException(s);  }


    public static Supplier<NullPointerException> CannotBeNull(Objects s){ return Exceptions.CannotBeNull(s.getS());  }
    public static Supplier<NullPointerException> CannotBeNull(String s){ return () -> new NullPointerException(s+" cannot be null.");  }


    public static Supplier<NotFoundException> NotFound(Objects s){ return Exceptions.NotFound(s.getS());  }
    public static Supplier<NotFoundException> NotFound(String s){ return () -> new NotFoundException(s+" not found");  }


    public static Supplier<DuplicateParameterException> AlreadyExists(Objects s){ return Exceptions.AlreadyExists(s.getS());  }
    public static Supplier<DuplicateParameterException> AlreadyExists(String s){ return () -> new DuplicateParameterException(s+" already exists");  }
}
