package com.plazoleta.user.domain.api;

import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
    void createEmployee(User employee, Long restaurantId);
    void createClient(User client);


    User findUserById(Long userId);

    Employee findEmployeeById(Long userId);
}
