package com.plazoleta.user.domain.exception;


import com.plazoleta.user.domain.util.ErrorMessages;
import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidUsernameOrPasswordException extends ErrorException {
    public InvalidUsernameOrPasswordException() {
        super(ExceptionConstants.INVALID_USERNAME_PASSWORD_ERROR, ExceptionConstants.INVALID_USERNAME_PASSWORD_MESSAGE, LocalDateTime.now().toString());
    }
}