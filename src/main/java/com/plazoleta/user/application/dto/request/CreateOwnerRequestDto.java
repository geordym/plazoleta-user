package com.plazoleta.user.application.dto.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.plazoleta.user.domain.util.Constants.MAX_IDENTITY_DOCUMENT_LENGTH;
import static com.plazoleta.user.domain.util.Constants.MIN_IDENTITY_DOCUMENT_LENGTH;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOwnerRequestDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "Identity document cannot be null")
    private Long identityDocument;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Phone number must be valid")
    private String phoneNumber;

    @NotBlank(message = "Date of birth cannot be blank")
    private String dateOfBirth; // Consider validating the format

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;


}