package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class InvalidIdentityDocumentException extends ErrorException{

    public InvalidIdentityDocumentException() {
        super(ExceptionConstants.IDENTITY_ERROR, ExceptionConstants.IDENTITY_INVALID_MESSAGE, LocalDateTime.now().toString());
    }
}
