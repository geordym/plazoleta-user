package com.plazoleta.user.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private Long id;
    private Long userId;
    private Long restaurantId;

    public Employee(Long userId, Long restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }
}
