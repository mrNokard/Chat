package com.nokard.chat.dto.attachment;

import com.nokard.chat.dto.BaseDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAttachmentResponse extends BaseDeleteResponse {
    private String filename;
}
