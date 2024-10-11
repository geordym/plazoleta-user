package com.plazoleta.user.domain.usecase.validation;

import com.plazoleta.user.domain.exception.*;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

import static com.plazoleta.user.domain.util.Constants.*;
import static com.plazoleta.user.domain.util.Constants.MINIUM_YEARS_AGE;

@RequiredArgsConstructor
public class UserUseCaseValidator {


    private final IUserPersistencePort userPersistencePort;

    public void validateCreateOwner(User owner){
        validateUniqueEmail(owner.getEmail());
        validateUniqueIdentityDocument(owner.getIdentityDocument());

        validateEmail(owner.getEmail());
        validatePhoneNumber(owner.getPhoneNumber());
        validateIdentityDocument(owner.getIdentityDocument());
        validateAge(owner.getDateOfBirth());
    }

    public void validateCreateEmployee(User employee){
        validateUniqueEmail(employee.getEmail());
        validateUniqueIdentityDocument(employee.getIdentityDocument());
        validateEmail(employee.getEmail());
        validateIdentityDocument(employee.getIdentityDocument());

    }

    public void validateCreateClient(User client){
        validateUniqueEmail(client.getEmail());
        validateUniqueIdentityDocument(client.getIdentityDocument());
        validateEmail(client.getEmail());
        validateIdentityDocument(client.getIdentityDocument());
    }

    private void validateUniqueIdentityDocument(Long identityDocument){
        boolean alreadyTaken = userPersistencePort.existsUserByIdentityDocument(identityDocument);
        if(alreadyTaken){
            throw new UserIdentityDocumentAlreadyTaken();
        }
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

    private void validateIdentityDocument(Long identityDocument) {
        if (identityDocument == null) {
            throw new InvalidIdentityDocumentException();
        }

        String identityDocumentStr = String.valueOf(identityDocument);
        int length = identityDocumentStr.length();

        if (length < MIN_IDENTITY_DOCUMENT_LENGTH || length > MAX_IDENTITY_DOCUMENT_LENGTH) {
            throw new InvalidIdentityDocumentException();
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

    public void validateUniqueEmail(String email){
        boolean existsUserByEmail = userPersistencePort.existsUserByEmail(email);
        if(existsUserByEmail){
            throw new UserEmailAlreadyTaken();
        }
    }

}
