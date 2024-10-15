package com.plazoleta.user.domain.exception;


import com.plazoleta.user.domain.util.ExceptionConstants;

import java.time.LocalDate;

public class RestaurantNotFoundException extends ErrorException{

    public RestaurantNotFoundException() {
        super(ExceptionConstants.RESTAURANT_NOT_FOUND_ERROR, ExceptionConstants.RESTAURANT_NOT_FOUND_ERROR_MESSAGE, LocalDate.now().toString());
    }
}
