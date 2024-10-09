package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidPhoneNumberException extends ErrorException{

    public InvalidPhoneNumberException() {
        super(ExceptionConstants.EMAIL_ERROR, ExceptionConstants.EMAIL_MESSAGE, LocalDateTime.now().toString());
    }

}
