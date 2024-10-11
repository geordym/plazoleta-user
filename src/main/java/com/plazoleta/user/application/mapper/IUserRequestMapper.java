package com.plazoleta.user.application.mapper;


import com.plazoleta.user.application.dto.request.CreateClientRequestDto;
import com.plazoleta.user.application.dto.request.CreateEmployeeRequestDto;
import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserRequestMapper {
    User toModel(CreateOwnerRequestDto createOwnerRequestDto);
    User toModel(CreateEmployeeRequestDto createEmployeeRequestDto);
    User toModel(CreateClientRequestDto createClientRequestDto);


}
