package com.plazoleta.user.domain.api;

import com.plazoleta.user.domain.model.other.AuthToken;

public interface IAuthenticationServicePort {

    AuthToken authenticateUser(String username, String password);

}
