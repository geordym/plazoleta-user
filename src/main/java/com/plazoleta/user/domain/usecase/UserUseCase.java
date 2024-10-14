package com.plazoleta.user.domain.usecase;

import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.exception.OwnershipViolationException;
import com.plazoleta.user.domain.exception.UserDoesNotExistException;
import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.model.external.Restaurant;
import com.plazoleta.user.domain.spi.IEmployeePersistencePort;
import com.plazoleta.user.domain.spi.IPlazoletaConnectionPort;
import com.plazoleta.user.domain.spi.IUserAuthenticationPort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import com.plazoleta.user.infraestructure.out.jpa.repository.IEmployeeRepository;
import io.github.classgraph.Resource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IPasswordEncoderPort passwordEncoderPort;
    private final IPlazoletaConnectionPort plazoletaConnectionPort;
    private final IEmployeePersistencePort employeePersistencePort;
    private final UserUseCaseValidator userUseCaseValidator;
    private final IUserAuthenticationPort userAuthenticationPort;
    private final IUserPersistencePort userPersistencePort;


    @Override
    public void createOwner(User owner) {
        userUseCaseValidator.validateCreateOwner(owner);
        saveUser(owner, RoleEnum.OWNER);
    }

    @Override
    public void createEmployee(User user, Long restaurantId) {
        userUseCaseValidator.validateCreateEmployee(user);
        Long ownerId = userAuthenticationPort.getAuthenticatedUserId();
        Restaurant restaurant = plazoletaConnectionPort.findRestaurantById(restaurantId).orElseThrow();
        validateRestaurantOwnership(ownerId, restaurant);

        User userSaved = saveUser(user, RoleEnum.EMPLOYEE);
        Employee employee = new Employee(userSaved.getId(), restaurant.getId());
        employeePersistencePort.saveEmployee(employee);
    }

    private void validateRestaurantOwnership(Long ownerId, Restaurant restaurant) {
        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new OwnershipViolationException();
        }
    }


    @Override
    public void createClient(User client) {
        userUseCaseValidator.validateCreateClient(client);
        saveUser(client, RoleEnum.CLIENTE);
    }

    private User saveUser(User owner, RoleEnum role) {
        owner.setRole(role.toModel());
        owner.setPassword(passwordEncoderPort.encode(owner.getPassword()));
        return userPersistencePort.saveUser(owner);
    }

    @Override
    public User findUserById(Long userId) {
        return userPersistencePort.findUserById(userId).orElseThrow(UserDoesNotExistException::new);
    }

    @Override
    public Employee findEmployeeById(Long userId) {
        return employeePersistencePort.findEmployeeByUserId(userId).orElseThrow(UserDoesNotExistException::new);
    }


}
