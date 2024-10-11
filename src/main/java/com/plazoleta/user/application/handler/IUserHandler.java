package com.plazoleta.user.application.handler;

import com.plazoleta.user.application.dto.request.CreateEmployeeRequestDto;
import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;

public interface IUserHandler {

    void createOwner(CreateOwnerRequestDto createOwnerRequestDto);
    void createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto);

    UserResponseDto findUserById(Long userId);

}
