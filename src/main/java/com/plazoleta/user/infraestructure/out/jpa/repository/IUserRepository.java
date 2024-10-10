package com.plazoleta.user.infraestructure.out.jpa.repository;

import com.plazoleta.user.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByIdentityDocument(Long identityDocument);

}
