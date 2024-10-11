package com.plazoleta.user.application.mapper;


import com.plazoleta.user.application.dto.response.AuthTokenResponseDto;
import com.plazoleta.user.domain.model.other.AuthToken;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IAuthenticationResponseMapper {

    AuthTokenResponseDto toDto(AuthToken authToken);
}
