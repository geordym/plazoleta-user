package com.plazoleta.user.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthTokenResponseDto {
    private final String accessToken;
    private final String refreshToken;

}