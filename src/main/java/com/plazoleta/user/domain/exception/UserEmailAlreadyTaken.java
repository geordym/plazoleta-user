package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class UserEmailAlreadyTaken extends ErrorException{
    public UserEmailAlreadyTaken() {
        super(ExceptionConstants.EMAIL_ERROR, ExceptionConstants.EMAIL_ALREADY_TAKEN_MESSAGE, LocalDateTime.now().toString());
    }
}
