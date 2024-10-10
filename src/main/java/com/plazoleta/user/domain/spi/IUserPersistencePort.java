package com.plazoleta.user.domain.spi;


import com.plazoleta.user.domain.model.User;

public interface IUserPersistencePort {
    User saveOwner(User user);
    boolean existsUserByEmail(String email);
    boolean existsUserByIdentityDocument(Long identityDocument);
}
