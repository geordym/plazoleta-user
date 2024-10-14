package com.plazoleta.user.infraestructure.input.rest;


import com.plazoleta.user.application.dto.request.CreateClientRequestDto;
import com.plazoleta.user.application.dto.request.CreateEmployeeRequestDto;
import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.application.dto.response.EmployeeResponseDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;
import com.plazoleta.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @Operation(summary = "Find Restaurant who works Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee exists and return the employee data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee with the given id cannot be founded", content = @Content),
    })
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> findEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeResponseDto employeeResponseDto = userHandler.findEmployeeById(employeeId);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }



    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exists and return that", content = @Content),
            @ApiResponse(responseCode = "404", description = "An user with the id given cannot be found", content = @Content),
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable("userId") Long userId) {
        UserResponseDto userResponseDto = userHandler.findUserById(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @Operation(summary = "Create a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, identity or email malformed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email or identity document already exists", content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> createOwner(@RequestBody @Valid CreateOwnerRequestDto createOwnerRequestDto) {
        userHandler.createOwner(createOwnerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, identity or email malformed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email or identity document already exists", content = @Content)
    })
    @PostMapping("/employee")
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid CreateEmployeeRequestDto createEmployeeRequestDto) {
        userHandler.createEmployee(createEmployeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, identity or email malformed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email or identity document already exists", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> createClient(@RequestBody @Valid CreateClientRequestDto createClientRequestDto) {
        userHandler.createClient(createClientRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
