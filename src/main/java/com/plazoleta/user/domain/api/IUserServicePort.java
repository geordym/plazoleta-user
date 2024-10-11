package com.plazoleta.user.domain.api;

import com.plazoleta.user.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
    void createEmployee(User employee);


    User findUserById(Long userId);
}
