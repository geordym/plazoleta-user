package com.plazoleta.user.infraestructure.out.jpa.adapter;

import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.exception.UserDoesNotExistException;
import com.plazoleta.user.domain.model.Employee;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.entity.EmployeeEntity;
import com.plazoleta.user.infraestructure.out.jpa.entity.UserEntity;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IEmployeeRepository;
import com.plazoleta.user.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity userEntityMapped = userEntityMapper.toEntity(user);
        UserEntity userEntity =  userRepository.save(userEntityMapped);
        return userEntityMapper.toModel(userEntity);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsUserByIdentityDocument(Long identityDocument) {
        return userRepository.existsByIdentityDocument(identityDocument);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userEntityMapper::toModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntityMapper::toModel);
    }

    @Override
    public List<User> findAllUsersByRoleId(Long roleId) {
        List<UserEntity> userEntities = userRepository.findAllByRoleId(roleId);
        return userEntities.stream().map(userEntityMapper::toModel).toList();
    }


}
