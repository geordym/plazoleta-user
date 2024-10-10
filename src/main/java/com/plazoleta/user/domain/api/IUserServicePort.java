package com.plazoleta.user.domain.api;

import com.plazoleta.user.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
    User findUserById(Long userId);
}
