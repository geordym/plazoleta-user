package com.plazoleta.user.infraestructure.input.rest;


import com.plazoleta.user.application.dto.request.CreateClientRequestDto;
import com.plazoleta.user.application.dto.request.CreateEmployeeRequestDto;
import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.application.dto.response.EmployeeResponseDto;
import com.plazoleta.user.application.dto.response.UserResponseDto;
import com.plazoleta.user.application.handler.IUserHandler;
import com.plazoleta.user.infraestructure.input.rest.constants.HttpStatusCodes;
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

import static com.plazoleta.user.infraestructure.input.rest.constants.RestEndpointConstants.REST_ENDPOINT_USERS_BASE;
import static com.plazoleta.user.infraestructure.input.rest.constants.RestEndpointConstants.REST_ENDPOINT_USERS_EMPLOYEE_FIND_EMPLOYEE_BYID;
import static com.plazoleta.user.infraestructure.input.rest.constants.SwaggerConstants.*;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping(REST_ENDPOINT_USERS_BASE)
@RequiredArgsConstructor
public class UserController {

    public static final String USER_FINDBYID_OK_DESCRIPTION = "User exists and return that";
    public static final String USER_FINDBYID_NOTFOUND_DESCRIPTION = "An user with the id given cannot be found";
    public static final String USER_FINDBYID_ROUTE = "/{userId}";
    public static final String USER_CREATE_OWNER_OPERATION = "Create a new owner";
    public static final String OWNER_CREATE_ON_CREATED = "Owner created";
    public static final String OWNER_CREATE_ON_BAD_REQUEST = "Bad request, identity or email malformed";
    public static final String OWNER_CREATE_ON_CONFLICT = "Email or identity document already exists";
    private final IUserHandler userHandler;

    @Operation(summary = FIND_EMPLOYEE_BY_ID_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_OK, description = FIND_EMPLOYEE_BY_ID_DESCRIPTION_OK, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_NOT_FOUND, description = FIND_EMPLOYEE_BY_ID_DESCRIPTION_NOTFOUND, content = @Content),
    })
    @GetMapping(REST_ENDPOINT_USERS_EMPLOYEE_FIND_EMPLOYEE_BYID)
    public ResponseEntity<EmployeeResponseDto> findEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeResponseDto employeeResponseDto = userHandler.findEmployeeById(employeeId);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }



    @Operation(summary = OPERATION_FIND_USER_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_OK, description = USER_FINDBYID_OK_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_NOT_FOUND, description = USER_FINDBYID_NOTFOUND_DESCRIPTION, content = @Content),
    })
    @GetMapping(USER_FINDBYID_ROUTE)
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable("userId") Long userId) {
        UserResponseDto userResponseDto = userHandler.findUserById(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @Operation(summary = USER_CREATE_OWNER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CREATED, description = OWNER_CREATE_ON_CREATED, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_BAD_REQUEST, description = OWNER_CREATE_ON_BAD_REQUEST, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CONFLICT, description = OWNER_CREATE_ON_CONFLICT, content = @Content)
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
