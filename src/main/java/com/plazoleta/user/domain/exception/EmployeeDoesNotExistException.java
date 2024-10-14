package com.plazoleta.user.domain.exception;

import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class EmployeeDoesNotExistException extends ErrorException{

    public EmployeeDoesNotExistException() {
        super(ExceptionConstants.EMPLOYEE_DOESNOT_EXIST_ERROR, ExceptionConstants.EMPLOYEE_DOESNOT_EXIST_MESSAGE, LocalDate.now().toString());
    }
}