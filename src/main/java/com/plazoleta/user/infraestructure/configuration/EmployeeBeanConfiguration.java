package com.plazoleta.user.infraestructure.configuration;


import com.plazoleta.user.domain.spi.IEmployeePersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.adapter.EmployeeJpaAdapter;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmployeeBeanConfiguration {

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Bean
    public IEmployeePersistencePort employeePersistencePort(){
        return new EmployeeJpaAdapter(employeeRepository, employeeEntityMapper);
    }

}
