package com.nokard.chat.config;

import com.nokard.chat.dto.exception.ExceptionResponse;
import com.nokard.chat.exception.BadRequestException;
import com.nokard.chat.exception.DuplicateParameterException;
import com.nokard.chat.exception.NotFoundException;
import com.nokard.chat.exception.ParameterNullException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.config.Order;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Log4j2
@Primary
@RestControllerAdvice
@Order(1)
public class ExceptionAdvice {
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handle(Throwable ex) {
        //Back in 1917
        String path = "";
        String method = "";

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            path = ((ServletRequestAttributes) attributes).getRequest().getRequestURI();
            method = ((ServletRequestAttributes) attributes).getRequest().getMethod();
        }

        HttpStatus status = status(ex);
        String logMessage = String.format("[%s] for %s %s", ex.getClass(), method, path);

        if (status == HttpStatus.INTERNAL_SERVER_ERROR)
            log.error(logMessage, ex);
        else
            log.warn(logMessage);
        //return to our days
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionResponse(ex));

    }

    private HttpStatus status(Throwable ex) {
        if (ex instanceof NotFoundException) return HttpStatus.NOT_FOUND;
        if (ex instanceof BadRequestException) return HttpStatus.BAD_REQUEST;
        if (ex instanceof DuplicateParameterException) return HttpStatus.BAD_REQUEST;
        if (ex instanceof ParameterNullException) return HttpStatus.BAD_REQUEST;
        if (ex instanceof TransactionSystemException) return HttpStatus.BAD_REQUEST;

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
