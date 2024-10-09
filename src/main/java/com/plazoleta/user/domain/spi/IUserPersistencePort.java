package com.plazoleta.user.domain.spi;


import com.plazoleta.user.domain.model.User;

public interface IUserPersistencePort {
    void saveOwner(User user);

}
