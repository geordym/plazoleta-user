package com.plazoleta.user.infraestructure.input.rest;


import com.plazoleta.user.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;



}
