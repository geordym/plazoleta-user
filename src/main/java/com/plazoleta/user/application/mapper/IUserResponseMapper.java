package com.plazoleta.user.application.mapper;


import com.plazoleta.user.application.dto.response.EmployeeResponseDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;
import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRoleResponseMapper.class}
)
public interface IUserResponseMapper {


    UserResponseDto toDto(User user);

    EmployeeResponseDto toDto(Employee employee);
}
