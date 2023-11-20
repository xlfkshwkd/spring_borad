package com.simsimhi.commons.exceptions;

import org.springframework.http.HttpStatus;


public class CommonException extends RuntimeException{
    //오류 코드 설정
    private HttpStatus status;

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}