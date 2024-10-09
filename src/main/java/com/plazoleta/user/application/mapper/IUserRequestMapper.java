package com.plazoleta.user.application.mapper;


import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface IUserRequestMapper {

    User toModel(CreateOwnerRequestDto createOwnerRequestDto);
}
