package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidPhoneNumberException extends ErrorException{

    public InvalidPhoneNumberException() {
        super(ExceptionConstants.PHONE_NUMBER_ERROR, ExceptionConstants.PHONE_NUMBER_MESSAGE, LocalDateTime.now().toString());
    }

}
