package com.plazoleta.user.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;
    private Long userId;
    private Long restaurantId;
}
