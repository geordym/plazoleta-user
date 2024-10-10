package com.plazoleta.user.infraestructure.input.rest;


import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

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

}
