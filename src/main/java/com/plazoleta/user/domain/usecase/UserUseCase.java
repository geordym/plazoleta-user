package com.plazoleta.user.domain.usecase;

import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.exception.InvalidEmailException;
import com.plazoleta.user.domain.exception.InvalidIdentityDocument;
import com.plazoleta.user.domain.exception.InvalidPhoneNumberException;
import com.plazoleta.user.domain.exception.MinorAgeException;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

import static com.plazoleta.user.domain.util.Constants.*;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserUseCaseValidator userUseCaseValidator;

    @Override
    public void createOwner(User owner) {
        userUseCaseValidator.validateCreateOwner(owner);
        userPersistencePort.saveOwner(owner);
    }



}
