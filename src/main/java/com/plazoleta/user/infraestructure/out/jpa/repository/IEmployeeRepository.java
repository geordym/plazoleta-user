package com.plazoleta.user.infraestructure.out.jpa.repository;

import com.plazoleta.user.infraestructure.out.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT e FROM EmployeeEntity e WHERE e.userEntity.id = :userId")
    Optional<EmployeeEntity> findByUserId(Long userId);
}
