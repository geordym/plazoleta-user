package com.plazoleta.user.infraestructure.configuration;


import com.plazoleta.user.application.handler.IUserHandler;
import com.plazoleta.user.application.handler.impl.UserHandlerImpl;
import com.plazoleta.user.application.mapper.IUserRequestMapper;
import com.plazoleta.user.application.mapper.IUserResponseMapper;
import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.IRolePersistencePort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.spi.security.ITokenProviderPort;
import com.plazoleta.user.domain.usecase.UserUseCase;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import com.plazoleta.user.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.plazoleta.user.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IRoleRepository;
import com.plazoleta.user.infraestructure.out.jpa.repository.IUserRepository;
import com.plazoleta.user.infraestructure.adapter.security.JwtIOTokenAdapter;
import com.plazoleta.user.infraestructure.adapter.security.PasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ITokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public UserUseCaseValidator userUseCaseValidator(){
        return new UserUseCaseValidator(userPersistencePort());
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), userUseCaseValidator(), passwordEncoderPort());
    }

    @Bean
    public IUserHandler userHandler(){
        return new UserHandlerImpl(userRequestMapper, userResponseMapper, userServicePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(){
        return new PasswordEncoderAdapter(bCryptPasswordEncoder());
    }

}
