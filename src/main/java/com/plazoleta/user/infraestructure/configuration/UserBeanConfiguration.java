package com.plazoleta.user.infraestructure.configuration;


import com.plazoleta.user.application.handler.IUserHandler;
import com.plazoleta.user.application.handler.impl.UserHandlerImpl;
import com.plazoleta.user.application.mapper.IUserRequestMapper;
import com.plazoleta.user.application.mapper.IUserResponseMapper;
import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.spi.*;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.usecase.UserUseCase;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import com.plazoleta.user.infraestructure.adapter.auth.UserAuthenticationAdapter;
import com.plazoleta.user.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserBeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    private final IEmployeePersistencePort employeePersistencePort;
    private final IPlazoletaConnectionPort plazoletaConnectionPort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IRoleEntityMapper roleEntityMapper;


    @Bean
    public IUserAuthenticationPort userAuthenticationPort(){
        return new UserAuthenticationAdapter(userPersistencePort());
    }


    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(passwordEncoderPort, plazoletaConnectionPort, employeePersistencePort, userUseCaseValidator(), userAuthenticationPort(), userPersistencePort());
    }

    @Bean
    public UserUseCaseValidator userUseCaseValidator(){
        return new UserUseCaseValidator(userPersistencePort());
    }

    @Bean
    public IUserHandler userHandler(){
        return new UserHandlerImpl(userRequestMapper, userResponseMapper, userServicePort());
    }


}
