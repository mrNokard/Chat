package com.nokard.chat.dto.exception;

import com.nokard.chat.utils.DateUtils;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private boolean error = true;
    private String message;
    private String type;
    private long timestamp = DateUtils.nowUnix();

    public ExceptionResponse(Throwable t){
        message = t.getLocalizedMessage();
        type = t.getClass().getSimpleName();
    }
}
