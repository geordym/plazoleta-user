package com.plazoleta.user.application.mapper;

import com.plazoleta.user.application.dto.response.RoleResponseDto;
import com.plazoleta.user.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)public interface IRoleResponseMapper {

    RoleResponseDto toDto(Role role);
}
