package com.springboot.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
