package com.plazoleta.user.application.handler.impl;

import com.plazoleta.user.application.dto.request.CreateClientRequestDto;
import com.plazoleta.user.application.dto.request.CreateEmployeeRequestDto;
import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.application.dto.response.EmployeeResponseDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;
import com.plazoleta.user.application.handler.IUserHandler;
import com.plazoleta.user.application.mapper.IUserRequestMapper;
import com.plazoleta.user.application.mapper.IUserResponseMapper;
import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IUserServicePort userServicePort;

    @Transactional
    @Override
    public void createOwner(CreateOwnerRequestDto createOwnerRequestDto) {
        User user = userRequestMapper.toModel(createOwnerRequestDto);
        userServicePort.createOwner(user);
    }

    @Transactional
    @Override
    public void createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto) {
        User user = userRequestMapper.toModel(createEmployeeRequestDto);
        Long restaurantId = createEmployeeRequestDto.getRestaurantId();
        userServicePort.createEmployee(user, createEmployeeRequestDto.getRestaurantId());
    }

    @Transactional
    @Override
    public void createClient(CreateClientRequestDto createClientRequestDto) {
        User user = userRequestMapper.toModel(createClientRequestDto);
        userServicePort.createClient(user);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        User user = userServicePort.findUserById(userId);
        return userResponseMapper.toDto(user);
    }

    @Override
    public EmployeeResponseDto findEmployeeById(Long userId) {
        return userResponseMapper.toDto(userServicePort.findEmployeeById(userId));
    }

}
