package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class UserIdentityDocumentAlreadyTaken extends ErrorException{
    public UserIdentityDocumentAlreadyTaken() {
        super(ExceptionConstants.IDENTITY_ERROR, ExceptionConstants.IDENTITY_ALREADY_TAKEN_MESSAGE, LocalDateTime.now().toString());
    }
}
