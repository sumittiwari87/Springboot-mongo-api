package com.mycloudknowledge.evaluation.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4684882617380399159L;

    public BookNotFoundException(String exception) {
        super(exception);
    }
}
