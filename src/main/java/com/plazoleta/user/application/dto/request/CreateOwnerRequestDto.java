package com.plazoleta.user.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateOwnerRequestDto {
    private String name;
    private String lastName;
    private Long identityDocument;
    private String phoneNumber;
    private String dateOfBirth;
    private String email;
    private String password;
    private Long roleId;
}
