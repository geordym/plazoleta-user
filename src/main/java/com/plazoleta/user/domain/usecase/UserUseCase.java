package com.plazoleta.user.domain.usecase;

import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.exception.UserDoesNotExistException;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserUseCaseValidator userUseCaseValidator;
    private final IPasswordEncoderPort passwordEncoderPort;

    @Override
    public void createOwner(User owner) {
        userUseCaseValidator.validateCreateOwner(owner);
        owner.setRole(RoleEnum.OWNER.toModel());
        owner.setPassword(passwordEncoderPort.encode(owner.getPassword()));
        userPersistencePort.saveOwner(owner);
    }

    @Override
    public User findUserById(Long userId) {
        User user = userPersistencePort.findUserById(userId).orElseThrow(UserDoesNotExistException::new);
        return user;
    }


}
