package com.plazoleta.user.domain.spi;

import com.plazoleta.user.domain.model.Role;

public interface IRolePersistencePort {
    boolean existsRolById(Long id);
    Role saveRole(Role role);
}
