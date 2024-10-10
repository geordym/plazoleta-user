package com.plazoleta.user.application.handler;

import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;

public interface IUserHandler {

    void createOwner(CreateOwnerRequestDto createOwnerRequestDto);

    UserResponseDto findUserById(Long userId);

}
