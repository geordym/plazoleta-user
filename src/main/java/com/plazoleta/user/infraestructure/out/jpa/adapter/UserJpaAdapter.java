package com.plazoleta.user.infraestructure.out.jpa.adapter;

import com.plazoleta.user.domain.exception.UserDoesNotExistException;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.infraestructure.out.jpa.entity.UserEntity;
import com.plazoleta.user.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.user.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public User saveOwner(User user) {
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


}
