package com.plazoleta.user.infraestructure.out.jpa.mapper;


import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.infraestructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRoleEntityMapper {
    RoleEntity toEntity(Role roleModel);

    Role toDomain(RoleEntity roleEntity);
}
