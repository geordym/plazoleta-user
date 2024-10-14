package com.plazoleta.user.domain.exception;


import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class ExternalConnectionException extends ErrorException{

    public ExternalConnectionException() {
        super(ExceptionConstants.EXTERNAL_CONNECTION_ERROR, ExceptionConstants.EXTERNAL_CONNECTION_MESSAGE, LocalDate.now().toString());
    }
}
