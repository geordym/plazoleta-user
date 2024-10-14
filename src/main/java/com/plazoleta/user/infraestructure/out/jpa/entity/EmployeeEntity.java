package com.plazoleta.user.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;
}
