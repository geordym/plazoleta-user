package com.plazoleta.user.infraestructure.out.jpa.repository;

import com.plazoleta.user.infraestructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
