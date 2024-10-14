package com.plazoleta.user.domain.spi;


import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;

public interface IUserAuthenticationPort {
    User getAuthenticatedUser();
    Long getAuthenticatedUserId();
    Role getAuthenticatedUserRole();
}
