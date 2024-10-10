package com.plazoleta.user.infraestructure.out.jpa.adapter;

import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.spi.IRolePersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.entity.RoleEntity;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public boolean existsRolById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public Role saveRole(Role role) {
        RoleEntity roleEntity = roleRepository.save(roleEntityMapper.toEntity(role));
        return roleEntityMapper.toDomain(roleEntity);
    }


}
