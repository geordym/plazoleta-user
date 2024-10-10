package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class MinorAgeException extends ErrorException{

    public MinorAgeException() {
        super(ExceptionConstants.MINOR_AGE_ERROR, ExceptionConstants.MINOR_AGE_MESSAGE, LocalDateTime.now().toString());
    }
}
