package com.plazoleta.user.application.handler.impl;

import com.plazoleta.user.application.dto.request.AuthenticationRequestDto;
import com.plazoleta.user.application.dto.response.AuthTokenResponseDto;
import com.plazoleta.user.application.handler.IAuthenticationHandler;
import com.plazoleta.user.application.mapper.IAuthenticationResponseMapper;
import com.plazoleta.user.domain.api.IAuthenticationServicePort;
import com.plazoleta.user.domain.model.other.AuthToken;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationHandlerImpl implements IAuthenticationHandler {

    private final IAuthenticationServicePort authenticationServicePort;
    private final IAuthenticationResponseMapper authenticationResponseMapper;

    @Override
    public AuthTokenResponseDto authenticateUser(AuthenticationRequestDto authenticationRequestDto) {
        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();
        AuthToken authToken = authenticationServicePort.authenticateUser(username, password);
        return authenticationResponseMapper.toDto(authToken);
    }

}
