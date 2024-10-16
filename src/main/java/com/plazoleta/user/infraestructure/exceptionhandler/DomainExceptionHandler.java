package com.plazoleta.user.infraestructure.exceptionhandler;


import com.plazoleta.user.application.dto.response.ErrorGenericResponseDto;
import com.plazoleta.user.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleRestaurantNotFoundException(RestaurantNotFoundException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(OwnershipViolationException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleOwnershipViolationException(ExternalConnectionException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(errorGenericResponseDto);
    }


    @ExceptionHandler(ExternalConnectionException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleExternalConnectionException(ExternalConnectionException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleUserDoesNotExistException(UserDoesNotExistException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidEmailException(InvalidEmailException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidIdentityDocumentException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidIdentityDocumentException(InvalidIdentityDocumentException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(MinorAgeException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidPhoneNumberException(MinorAgeException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(UserEmailAlreadyTaken.class)
    public ResponseEntity<ErrorGenericResponseDto> handleUserEmailAlreadyTakenException(UserEmailAlreadyTaken ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(UserIdentityDocumentAlreadyTaken.class)
    public ResponseEntity<ErrorGenericResponseDto> handleUserIdentityDocumentAlreadyTaken(UserIdentityDocumentAlreadyTaken ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorGenericResponseDto);
    }




}
