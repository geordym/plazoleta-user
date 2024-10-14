package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDateTime;

public class OwnershipViolationException extends ErrorException{

    public OwnershipViolationException() {
        super(ExceptionConstants.OWNERSHIP_VIOLATION_ERROR, ExceptionConstants.OWNERSHIP_VIOLATION_MESSAGE, LocalDateTime.now().toString());
    }
}
