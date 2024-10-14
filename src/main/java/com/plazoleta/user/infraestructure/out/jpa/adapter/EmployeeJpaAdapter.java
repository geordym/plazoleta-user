package com.plazoleta.user.infraestructure.out.jpa.adapter;

import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.domain.spi.IEmployeePersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.entity.EmployeeEntity;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeePersistencePort {

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.save(employeeEntityMapper.toEntity(employee));
        return employeeEntityMapper.toModel(employeeEntity);
    }

    @Override
    public Optional<Employee> findEmployeeByUserId(Long userId) {
        return employeeRepository.findByUserId(userId).map(employeeEntityMapper::toModel);
    }

}
