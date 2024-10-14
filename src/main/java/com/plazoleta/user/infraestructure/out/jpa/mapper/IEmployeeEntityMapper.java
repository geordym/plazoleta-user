package com.plazoleta.user.infraestructure.out.jpa.mapper;

import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.infraestructure.out.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IEmployeeEntityMapper {

    @Mapping(source = "userId", target = "userEntity.id")
    EmployeeEntity toEntity(Employee employee);

    @Mapping(source = "userEntity.id", target = "userId")
    Employee toModel(EmployeeEntity employee);

}
