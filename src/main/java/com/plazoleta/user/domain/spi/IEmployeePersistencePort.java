package com.plazoleta.user.domain.spi;

import com.plazoleta.user.domain.model.Employee;

import java.util.Optional;

public interface IEmployeePersistencePort {
    Employee saveEmployee(Employee employee);
    Optional<Employee> findEmployeeByUserId(Long userId);

}
