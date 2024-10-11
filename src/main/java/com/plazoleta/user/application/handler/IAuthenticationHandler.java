package com.plazoleta.user.application.handler;


import com.plazoleta.user.application.dto.request.AuthenticationRequestDto;
import com.plazoleta.user.application.dto.response.AuthTokenResponseDto;

public interface IAuthenticationHandler {

    AuthTokenResponseDto authenticateUser(AuthenticationRequestDto authenticationRequestDto);

}
