package com.plazoleta.user.infraestructure.out.jpa.seeder;

import com.plazoleta.user.domain.api.IUserServicePort;
import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IRolePersistencePort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.infraestructure.out.jpa.entity.RoleEntity;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner, Ordered {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;

    @Override
    public void run(String... args) throws Exception {


        List<User> users = userPersistencePort.findAllUsersByRoleId(RoleEnum.ADMINISTRATOR.getId());
        if(users.isEmpty()){
            User user = new User();
            user.setName("administrator");
            user.setEmail("administrator");
            user.setPassword(passwordEncoderPort.encode("administrator"));
            user.setIdentityDocument(11111111L);
            user.setPhoneNumber("+573026468094");
            user.setLastName("administrator");
            user.setRole(RoleEnum.ADMINISTRATOR.toModel());
            userPersistencePort.saveUser(user);
            System.out.println("The administrators cannot be founded, creating a default administrator");

        }
    }


    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}