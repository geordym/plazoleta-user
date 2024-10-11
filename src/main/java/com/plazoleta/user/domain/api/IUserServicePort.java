package com.plazoleta.user.domain.api;

import com.plazoleta.user.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
    void createEmployee(User employee);
    void createClient(User client);


    User findUserById(Long userId);
}
