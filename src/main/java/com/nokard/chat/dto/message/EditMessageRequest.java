package com.nokard.chat.dto.message;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EditMessageRequest extends BaseMessageRequest {
    public EditMessageRequest(Long id, Long idAuthor, String content) {
        super(id, idAuthor, content);
    }
}
