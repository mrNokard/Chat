package com.nokard.chat.dto.message;

import com.nokard.chat.dto.BaseDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteMessageResponse extends BaseDeleteResponse {
    private String content;
}
