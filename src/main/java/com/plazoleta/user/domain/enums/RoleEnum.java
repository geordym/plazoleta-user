package com.plazoleta.user.domain.enums;

import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.infraestructure.out.jpa.entity.RoleEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum RoleEnum {
    ADMINISTRATOR(1L, "ROLE_ADMINISTRATOR", "ADMINISTRATOR"),
    CLIENTE(2L, "ROLE_CLIENT", "CLIENT"),
    OWNER(3L, "ROLE_OWNER", "OWNER"),
    EMPLOYEE(4L, "ROLE_EMPLOYEE", "EMPLOYEE");


    private final Long id;
    private final String nameBd;
    private final String name;

    RoleEnum(Long id, String nameDb, String name) {
        this.id = id;
        this.nameBd = nameDb;
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public String getNameBd() {
        return nameBd;
    }

    public String getName() {
        return name;
    }

    // Método para obtener un Role por su nombre
    public static RoleEnum fromName(String name) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getName().equalsIgnoreCase(name)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el nombre: " + name);
    }

    // Método para obtener un Role por su ID
    public static RoleEnum fromId(Long id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getId() == id) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el ID: " + id);
    }

    public static List<RoleEnum> getAllRoles() {
        return Arrays.asList(RoleEnum.values());
    }

    public static List<Long> getAllIds() {
        return Arrays.stream(values())
                .map(RoleEnum::getId)
                .collect(Collectors.toList());
    }

    public RoleEntity toEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(this.id);
        roleEntity.setName(this.nameBd);
        return roleEntity;
    }

    public Role toModel() {
        Role role = new Role(this.id, this.name);
        return role;
    }

}