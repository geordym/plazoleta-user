package com.plazoleta.user.infraestructure.out.jpa.mapper;


import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.infraestructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRoleEntityMapper.class}
)
public interface IUserEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "identityDocument", target = "identityDocument")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "role", target = "role")
    UserEntity toEntity(User userModel);


    User toModel(UserEntity userEntity);

}
