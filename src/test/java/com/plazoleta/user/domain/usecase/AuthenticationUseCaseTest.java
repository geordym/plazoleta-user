package com.plazoleta.user.domain.usecase;


import com.plazoleta.user.domain.exception.InvalidUsernameOrPasswordException;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.model.other.AuthToken;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.security.ITokenProviderPort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.plazoleta.user.domain.util.Constants.*;
import static com.plazoleta.user.domain.util.JwtSecurityConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationUseCaseTest {


    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private ITokenProviderPort tokenProviderPort;

    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setup(){
        authenticationUseCase = new AuthenticationUseCase(passwordEncoderPort, userPersistencePort, tokenProviderPort);

    }


    @Test
    public void authenticateUser_WhenCalledWithNullData_ReturnException(){
        String username = null;
        String password = null;

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    @Test
    public void authenticateUser_WhenCalledWithEmailLengthLower_ReturnException(){
        String username = "a".repeat(SECURITY_EMAIL_MIN_LENGTH - 1 );
        String password = "".repeat(SECURITY_PASSWORD_MIN_LENGTH);

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    @Test
    public void authenticateUser_WhenCalledWithPasswordLengthLower_ReturnException(){
        String username = "a".repeat(SECURITY_EMAIL_MIN_LENGTH);
        String password = "a".repeat(SECURITY_PASSWORD_MIN_LENGTH - 1);

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    @Test
    public void authenticateUser_WhenCalledWithAUserDoesNotExist_ReturnException(){
        String username = "a".repeat(SECURITY_EMAIL_MIN_LENGTH);
        String password = "a".repeat(SECURITY_PASSWORD_MIN_LENGTH);

        when(userPersistencePort.findByEmail(username)).thenReturn(Optional.empty());

        assertThrows(InvalidUsernameOrPasswordException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }



    static Stream<String> invalidPasswords() {
        return Stream.of(
                null,
                "",
                ""
        );
    }




    @ParameterizedTest
    @MethodSource("invalidPasswords")
    public void validatePassword_WhenCalledWithStorePasswordNullOrEmpty_ReturnException(String storedPassword){
        String providedPassword = "a".repeat(SECURITY_PASSWORD_MIN_LENGTH);

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @ParameterizedTest
    @MethodSource("invalidPasswords")
    public void validatePassword_WhenCalledWithProvidedPasswordNullOrEmpty_ReturnException(String providedPassword){
        String storedPassword = "a".repeat(SECURITY_PASSWORD_MIN_LENGTH);

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }


    @Test
    public void validatePassword_WhenCalledAndPasswordNotMatches_ReturnException(){
        String providedPassword = "testing4411";
        String storedPassword = "testing44";

        when(passwordEncoderPort.matches(providedPassword,storedPassword)).thenReturn(false);

        assertThrows(InvalidUsernameOrPasswordException.class, () -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @Test
    public void validatePassword_WhenCalledAndPasswordMatches_DoesNotReturnException(){
        String providedPassword = "testing44";
        String storedPassword = "testing44";

        when(passwordEncoderPort.matches(providedPassword,storedPassword)).thenReturn(true);

        assertDoesNotThrow(() -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @Test
    public void createPassword_WhenCalledWithAnUserNull_ReturnException(){
        User user = null;

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.createClaims(user));
    }

    @Test
    public void createClaimsWhenCalledWithValidUser_ReturnClaims(){
        Long id = 10L;
        String role = "ROLE";
        User user = new User();
        user.setId(id);
        user.setRole(new Role(0L, role));

        Map<String, Object> claims = authenticationUseCase.createClaims(user);

        assertEquals(claims.get(CLAIM_SUBJECT_KEY), String.valueOf(id));
        assertEquals(claims.get(KEY_ROLE_CLAIM), role);
    }


    @Test
    public void generateAccessToken_WithValidInputs_ReturnsToken() {
        // Arrange
        String subject = "testUser";
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "value");

        LocalDateTime now = LocalDateTime.now();

        // Mock the tokenProviderPort
        when(tokenProviderPort.generateAccessToken(
                any(LocalDateTime.class),
                eq(subject),
                any(LocalDateTime.class),
                eq(claims)))
                .thenReturn("mockAccessToken");

        // Act
        String token = authenticationUseCase.generateAccessToken(subject, claims);

        // Assert
        assertEquals("mockAccessToken", token);
    }

    @Test
    public void generateRefreshToken_WithValidInputs_ReturnsToken() {
        // Arrange
        String subject = "testUser";

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationAt = now.plusMinutes(REFRESH_TOKEN_DURATION_MINUTES);

        // Mock the tokenProviderPort
        when(tokenProviderPort.generateRefreshToken(any(), eq(subject), any()))
                .thenReturn("mockRefreshToken");

        // Act
        String token = authenticationUseCase.generateRefreshToken(subject);

        // Assert
        assertEquals("mockRefreshToken", token);
    }


    @Test
    public void authenticateUser_WithValidInputs_ReturnsAuthToken() {
        String username = "a".repeat(SECURITY_EMAIL_MIN_LENGTH);
        String password = "a".repeat(SECURITY_PASSWORD_MIN_LENGTH);
        String accessToken = "accesstoken";
        String refreshToken = "refreshtoken";
        String subject = "subject";
        Long id = 10L;
        String role = "ROLE";
        User user = new User();
        user.setId(id);
        user.setRole(new Role(0L, role));
        user.setEmail(username);
        user.setPassword(password);

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUBJECT_KEY, user.getId());
        claims.put(KEY_ROLE_CLAIM, user.getRole().getName());

        when(userPersistencePort.findByEmail(username)).thenReturn(Optional.of(user));
        when(tokenProviderPort.generateAccessToken(any(), any(), any(), anyMap())).thenReturn(accessToken);
        when(tokenProviderPort.generateRefreshToken(any(), any(), any())).thenReturn(refreshToken);
        when(passwordEncoderPort.matches(user.getPassword(), password)).thenReturn(true);

        AuthToken authToken = authenticationUseCase.authenticateUser(username, password);

        assertEquals(accessToken,authToken.getAccessToken());
        assertEquals(refreshToken,authToken.getRefreshToken());
    }



}
