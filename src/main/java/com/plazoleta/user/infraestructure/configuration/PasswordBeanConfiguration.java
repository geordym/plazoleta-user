package com.plazoleta.user.infraestructure.configuration;

import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.security.ITokenProviderPort;
import com.plazoleta.user.infraestructure.adapter.security.JwtIOTokenAdapter;
import com.plazoleta.user.infraestructure.adapter.security.PasswordEncoderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordBeanConfiguration {


    @Bean
    public ITokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(){
        return new PasswordEncoderAdapter(bCryptPasswordEncoder());
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
