package com.plazoleta.user.infraestructure.configuration;


import com.plazoleta.user.application.handler.IAuthenticationHandler;
import com.plazoleta.user.application.handler.impl.AuthenticationHandlerImpl;
import com.plazoleta.user.application.mapper.IAuthenticationResponseMapper;
import com.plazoleta.user.domain.api.IAuthenticationServicePort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.security.ITokenProviderPort;
import com.plazoleta.user.domain.usecase.AuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthenticationBeanConfiguration {

    private final IAuthenticationResponseMapper authenticationResponseMapper;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IUserPersistencePort userPersistencePort;
    private final ITokenProviderPort tokenProviderPort;

    @Bean
    public IAuthenticationServicePort authenticationServicePort(){
        return new AuthenticationUseCase(passwordEncoderPort, userPersistencePort, tokenProviderPort);
    }

    @Bean
    public IAuthenticationHandler authenticationHandler(){
        return new AuthenticationHandlerImpl(authenticationServicePort(), authenticationResponseMapper);
    }

}
