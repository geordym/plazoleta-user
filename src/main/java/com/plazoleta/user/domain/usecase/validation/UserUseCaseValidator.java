package com.plazoleta.user.domain.usecase.validation;

import com.plazoleta.user.domain.exception.InvalidEmailException;
import com.plazoleta.user.domain.exception.InvalidIdentityDocument;
import com.plazoleta.user.domain.exception.InvalidPhoneNumberException;
import com.plazoleta.user.domain.exception.MinorAgeException;
import com.plazoleta.user.domain.model.User;

import java.time.LocalDate;
import java.time.Period;

import static com.plazoleta.user.domain.util.Constants.*;
import static com.plazoleta.user.domain.util.Constants.MINIUM_YEARS_AGE;

public class UserUseCaseValidator {


    public void validateCreateOwner(User owner){
        validateEmail(owner.getEmail());
        validatePhoneNumber(owner.getPhoneNumber());
        validateIdentityDocument(owner.getIdentityDocument());
        validateAge(owner.getDateOfBirth());
    }


    private void validateEmail(String email){
        boolean isValidEmail = email.matches(EMAIL_REGEX);
        if(!isValidEmail){
            throw new InvalidEmailException();
        }
    }

    private void validatePhoneNumber(String phoneNumber){
        boolean hasInitialSymbol = phoneNumber.startsWith(NUMBER_PREFIX);
        if(!hasInitialSymbol){
            throw new InvalidPhoneNumberException();
        }

        boolean isValidPhoneNumber = phoneNumber.matches(PHONE_REGEX);
        if(!isValidPhoneNumber){
            throw new InvalidPhoneNumberException();
        }
    }

    private void validateIdentityDocument(Long identityDocument){
        boolean isValidIdentityDocument = true; //TODO: Add the bussinness rule to validate identity document
        if(!isValidIdentityDocument){
            throw new InvalidIdentityDocument();
        }
    }

    private void validateAge(LocalDate dateOfBirth){
        if (dateOfBirth == null) {
            throw new IllegalArgumentException();
        }

        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if (age < MINIUM_YEARS_AGE) {
            throw new MinorAgeException();
        }
    }
}
