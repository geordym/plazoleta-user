package com.plazoleta.user.infraestructure.configuration;


import com.plazoleta.user.domain.spi.IRolePersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoleBeanConfiguration {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }


}
