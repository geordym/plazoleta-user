package com.plazoleta.user.infraestructure.adapter.auth;

import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.IUserAuthenticationPort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.infraestructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class UserAuthenticationAdapter implements IUserAuthenticationPort {

    private final IUserPersistencePort userPersistencePort;

    @Override
    public User getAuthenticatedUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPersistencePort.findUserById(Long.valueOf(customUserDetails.getUserId())).orElseThrow();
    }

    @Override
    public Long getAuthenticatedUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf(customUserDetails.getUserId());
    }

    @Override
    public Role getAuthenticatedUserRole() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String firstAuthority = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);

        return RoleEnum.fromName(firstAuthority).toModel();
    }



}
