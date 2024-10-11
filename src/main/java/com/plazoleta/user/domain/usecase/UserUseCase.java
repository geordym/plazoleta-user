package com.plazoleta.user.domain.usecase;

import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.exception.UserDoesNotExistException;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserUseCaseValidator userUseCaseValidator;
    private final IPasswordEncoderPort passwordEncoderPort;

    @Override
    public void createOwner(User owner) {
        userUseCaseValidator.validateCreateOwner(owner);
        saveUser(owner, RoleEnum.OWNER);
    }

    @Override
    public void createEmployee(User employee) {
        userUseCaseValidator.validateCreateEmployee(employee);
        saveUser(employee, RoleEnum.EMPLOYEE);
    }

    private void saveUser(User owner, RoleEnum role) {
        owner.setRole(role.toModel());
        owner.setPassword(passwordEncoderPort.encode(owner.getPassword()));
        userPersistencePort.saveUser(owner);
    }

    @Override
    public User findUserById(Long userId) {
        return userPersistencePort.findUserById(userId).orElseThrow(UserDoesNotExistException::new);
    }


}
