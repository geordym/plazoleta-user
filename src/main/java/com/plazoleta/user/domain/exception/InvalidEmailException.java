package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidEmailException extends ErrorException{

    public InvalidEmailException() {
        super(ExceptionConstants.EMAIL_ERROR, ExceptionConstants.EMAIL_MESSAGE, LocalDateTime.now().toString());
    }

}
