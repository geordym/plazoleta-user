package com.plazoleta.user.domain.spi;


import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserPersistencePort {
    User saveUser(User user);
    boolean existsUserByEmail(String email);
    boolean existsUserByIdentityDocument(Long identityDocument);


    Optional<User> findUserById(Long userId);

    Optional<User> findByEmail(String email);

    List<User> findAllUsersByRoleId(Long roleId);

}
