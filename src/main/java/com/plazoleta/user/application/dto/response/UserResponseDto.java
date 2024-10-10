package com.plazoleta.user.application.dto.response;

import com.plazoleta.user.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private Long identityDocument;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    private RoleResponseDto role;
}
