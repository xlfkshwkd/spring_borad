package com.simsimhi.commons.exceptions;


import com.simsimhi.commons.Utils;

public class BadRequestException extends AlertBackException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() { // 오류 코드 받아오기
        super(Utils.getMessage("BadRequest", "error"));
    }

}
