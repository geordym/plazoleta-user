package com.plazoleta.user.infraestructure.out.jpa.seeder;


import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.spi.IRolePersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.entity.RoleEntity;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner, Ordered {

    private final IRolePersistencePort rolePersistencePort;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void run(String... args) throws Exception {
        List<RoleEntity> rolesEntitiesList = RoleEnum.getAllRoles().stream().map(roleEnum -> roleEnum.toEntity()).toList();
        List<RoleEntity> roleEntitiesFilter = rolesEntitiesList.stream().filter(roleEntity -> !rolePersistencePort.existsRolById(roleEntity.getId())).collect(Collectors.toList());

        roleEntitiesFilter.stream().forEach(roleEntity -> {
            System.out.println("The role with name: " + roleEntity.getName() + " doesnot exist in bd then will be created");
            rolePersistencePort.saveRole(roleEntityMapper.toDomain(roleEntity));
        });

    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}