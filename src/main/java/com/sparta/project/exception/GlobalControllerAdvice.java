package com.sparta.project.exception;


import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    private final String ERROR_LOG = "[ERROR] %s %s";

    @ExceptionHandler(CodeBloomException.class)
    public ResponseEntity<ExceptionResponse> applicationException(final CodeBloomException e){
        log.error(String.format(ERROR_LOG, e.getHttpStatus(), e.getMessage()));
        return ResponseEntity.status(e.getHttpStatus()).body(new ExceptionResponse(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse httpReqMethodNotSupportException(final HttpRequestMethodNotSupportedException e){
        log.error(String.format(ERROR_LOG, e.getMessage(), Arrays.toString(e.getSupportedMethods())));
        return new ExceptionResponse("지원하지 않는 요청 방법입니다.");
    }

}
