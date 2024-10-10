package com.plazoleta.user.application.handler.impl;

import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;
import com.plazoleta.user.application.handler.IUserHandler;
import com.plazoleta.user.application.mapper.IUserRequestMapper;
import com.plazoleta.user.application.mapper.IUserResponseMapper;
import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IUserServicePort userServicePort;

    @Override
    public void createOwner(CreateOwnerRequestDto createOwnerRequestDto) {
        User user = userRequestMapper.toModel(createOwnerRequestDto);
        userServicePort.createOwner(user);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        User user = userServicePort.findUserById(userId);
        return userResponseMapper.toDto(user);
    }

}
