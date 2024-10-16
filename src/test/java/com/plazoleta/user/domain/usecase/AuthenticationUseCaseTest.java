package com.plazoleta.user.domain.usecase;


import com.plazoleta.user.domain.exception.InvalidUsernameOrPasswordException;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.model.other.AuthToken;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.security.ITokenProviderPort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import com.plazoleta.user.util.DataProvider;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
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
     void authenticateUser_WhenCalledWithNullData_ReturnException(){
        String username = null;
        String password = null;

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    @Test
     void authenticateUser_WhenCalledWithEmailLengthLower_ReturnException(){
        String username = DataProvider.getInvalidEmail();
        String password = DataProvider.getInvalidPassword();

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    @Test
     void authenticateUser_WhenCalledWithPasswordLengthLower_ReturnException(){
        String username = DataProvider.getInvalidEmail();
        String password = DataProvider.getInvalidPassword();

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    @Test
     void authenticateUser_WhenCalledWithAUserDoesNotExist_ReturnException(){
        String username = DataProvider.getValidUsername();
        String password = DataProvider.getValidRandomPassword();

        when(userPersistencePort.findByEmail(username)).thenReturn(Optional.empty());

        assertThrows(InvalidUsernameOrPasswordException.class, () -> authenticationUseCase.authenticateUser(username, password));
    }

    static Stream<String> invalidPasswords() {
        return DataProvider.invalidPasswords();
    }

    @ParameterizedTest
    @MethodSource("invalidPasswords")
     void validatePassword_WhenCalledWithStorePasswordNullOrEmpty_ReturnException(String storedPassword){
        String providedPassword = DataProvider.getInvalidPassword();

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @ParameterizedTest
    @MethodSource("invalidPasswords")
     void validatePassword_WhenCalledWithProvidedPasswordNullOrEmpty_ReturnException(String providedPassword){
        String storedPassword = DataProvider.getValidRandomPassword();

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @Test
     void validatePassword_WhenCalledAndPasswordNotMatches_ReturnException(){
        Pair<String, String> passwordsTuple = DataProvider.getPairPasswordNotMatches();
        String providedPassword = passwordsTuple.getLeft();
        String storedPassword = passwordsTuple.getRight();

        when(passwordEncoderPort.matches(providedPassword,storedPassword)).thenReturn(false);

        assertThrows(InvalidUsernameOrPasswordException.class, () -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @Test
     void validatePassword_WhenCalledAndPasswordMatches_DoesNotReturnException(){
        Pair<String, String> passwordsTuple = DataProvider.getPairPasswordMatches();
        String providedPassword = passwordsTuple.getLeft();
        String storedPassword = passwordsTuple.getRight();

        when(passwordEncoderPort.matches(providedPassword,storedPassword)).thenReturn(true);

        assertDoesNotThrow(() -> authenticationUseCase.validatePassword(providedPassword, storedPassword));
    }

    @Test
     void createPassword_WhenCalledWithAnUserNull_ReturnException(){
        User user = null;

        assertThrows(IllegalArgumentException.class, () -> authenticationUseCase.createClaims(user));
    }

    @Test
     void createClaimsWhenCalledWithValidUser_ReturnClaims(){
        User user = DataProvider.getUserValid();
        Map<String, Object> claims = authenticationUseCase.createClaims(user);

        assertEquals(claims.get(CLAIM_SUBJECT_KEY), String.valueOf(user.getId()));
        assertEquals(claims.get(KEY_ROLE_CLAIM), user.getRole().getName());
    }


    @Test
     void generateAccessToken_WithValidInputs_ReturnsToken() {
        String subject = DataProvider.getSubject();
        Map<String, Object> claims = DataProvider.getSimpleTestClaim();

        when(tokenProviderPort.generateAccessToken(
                any(LocalDateTime.class),
                eq(subject),
                any(LocalDateTime.class),
                eq(claims)))
                .thenReturn("mockAccessToken");
        String token = authenticationUseCase.generateAccessToken(subject, claims);

        assertEquals("mockAccessToken", token);
    }

    @Test
     void generateRefreshToken_WithValidInputs_ReturnsToken() {
        String subject = DataProvider.getSubject();

        when(tokenProviderPort.generateRefreshToken(any(), eq(subject), any()))
                .thenReturn("mockRefreshToken");
        String token = authenticationUseCase.generateRefreshToken(subject);

        assertEquals("mockRefreshToken", token);
    }


    @Test
     void authenticateUser_WithValidInputs_ReturnsAuthToken() {
        String accessToken = "accesstoken";
        String refreshToken = "refreshtoken";
        User user = DataProvider.getUserValid();

        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(tokenProviderPort.generateAccessToken(any(), any(), any(), anyMap())).thenReturn(accessToken);
        when(tokenProviderPort.generateRefreshToken(any(), any(), any())).thenReturn(refreshToken);
        when(passwordEncoderPort.matches(user.getPassword(), user.getPassword())).thenReturn(true);

        AuthToken authToken = authenticationUseCase.authenticateUser(user.getEmail(), user.getPassword());

        assertEquals(accessToken,authToken.getAccessToken());
        assertEquals(refreshToken,authToken.getRefreshToken());
    }



}
