package com.plazoleta.user.infraestructure.input.rest;

import com.plazoleta.user.application.dto.request.AuthenticationRequestDto;
import com.plazoleta.user.application.dto.response.AuthTokenResponseDto;
import com.plazoleta.user.application.handler.IAuthenticationHandler;
import com.plazoleta.user.infraestructure.input.rest.constants.HttpStatusCodes;
import com.plazoleta.user.infraestructure.input.rest.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationHandler authenticationRestHandler;

    @Operation(
            summary = SwaggerConstants.AUTHENTICATE_USER_SUMMARY,
            description = SwaggerConstants.AUTHENTICATE_USER_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CREATED, description = SwaggerConstants.AUTHENTICATE_USER_API_RESPONSES_200_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_NOT_FOUND, description = SwaggerConstants.AUTHENTICATE_USER_API_RESPONSES_404_DESCRIPTION, content = @Content),
    })
    @PostMapping
    public ResponseEntity<AuthTokenResponseDto> authenticateUser(@RequestBody @Valid AuthenticationRequestDto
                                                                              authenticationRequest)  {
        AuthTokenResponseDto authTokenResponseDto = authenticationRestHandler.authenticateUser(authenticationRequest);
        return new ResponseEntity<>(authTokenResponseDto, HttpStatus.OK);
    }





}
