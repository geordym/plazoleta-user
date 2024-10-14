package com.plazoleta.user.domain.spi;


import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    User saveUser(User user);
    boolean existsUserByEmail(String email);
    boolean existsUserByIdentityDocument(Long identityDocument);

    Optional<User> findUserById(Long userId);

    Optional<User> findByEmail(String email);



}
