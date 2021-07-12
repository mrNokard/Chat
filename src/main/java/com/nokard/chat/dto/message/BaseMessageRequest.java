package com.nokard.chat.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseMessageRequest {
    protected Long id;
    protected Long idAuthor;
    protected String content;
}
