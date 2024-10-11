package com.plazoleta.user.domain.usecase;


import com.plazoleta.user.domain.enums.RoleEnum;
import com.plazoleta.user.domain.exception.*;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.usecase.validation.UserUseCaseValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.plazoleta.user.domain.util.Constants.MINIUM_YEARS_AGE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {


    @Mock
    private IUserPersistencePort userPersistencePort;


    @Mock
    private IPasswordEncoderPort passwordEncoderPort;


    private UserUseCase userUseCase;
    private UserUseCaseValidator userUseCaseValidator;

    private User validUserOwner;

    @BeforeEach
    void setup(){
        userUseCaseValidator = new UserUseCaseValidator(userPersistencePort);
        userUseCase = new UserUseCase(userPersistencePort, userUseCaseValidator, passwordEncoderPort);

        validUserOwner = new User(0L, "David", "Montero", 1018522721L, "+573026468094", LocalDate.now().minusYears(MINIUM_YEARS_AGE), "ggeordymm@gmail.com", "testing1234");
    }

    @Test
    public void createOwner_WhenCalledWithValidData_DoesNotReturnException(){
        userUseCase.createOwner(validUserOwner);

        assertDoesNotThrow(() -> userUseCase.createOwner(validUserOwner));
    }

    static Stream<String> invalidEmails() {
        return Stream.of(
                "ggeordymmgmail.com",  // Sin @
                "invalidEmail@",       // Sin dominio
                "@invalid.com",        // Sin nombre de usuario
                "invalidEmail.com",    // Sin @ y sin nombre de usuario
                "invalid@.com",        // Dominio inválido
                "invalid@domain..com"  // Doble punto en el dominio
        );
    }

    @ParameterizedTest
    @MethodSource("invalidEmails")
    public void createOwner_WhenCalledWithInvalidEmail_ThrowsException(String invalidEmail) {
        User invalidOwner = validUserOwner;
        invalidOwner.setEmail(invalidEmail);
        assertThrows(InvalidEmailException.class, () -> userUseCase.createOwner(invalidOwner));
    }

    static Stream<String> invalidPhoneNumbers() {
        return Stream.of(
                "573026468094",    // Sin +
                "12345678901234",  // Más de 13 dígitos
                "+",                // Solo el símbolo +
                "+12345678901234", // Más de 13 dígitos con +
                "invalidPhone"     // No es numérico
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPhoneNumbers")
    public void createOwner_WhenCalledWithInvalidPhoneNumber_ThrowsException(String invalidPhone) {
        User invalidOwner = validUserOwner;
        invalidOwner.setPhoneNumber(invalidPhone);

        assertThrows(InvalidPhoneNumberException.class, () -> userUseCase.createOwner(invalidOwner));
    }

    static Stream<LocalDate> invalidDatesOfBirths() {
        return Stream.of(
                LocalDate.now(),
                LocalDate.now().minusYears(MINIUM_YEARS_AGE).plusYears(1L)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDatesOfBirths")
    public void createOwner_WhenCalledWithInvalidAge_ThrowsException(LocalDate dateOfBirth) {
        User invalidOwner = validUserOwner;
        invalidOwner.setDateOfBirth(dateOfBirth);

        assertThrows(MinorAgeException.class, () -> userUseCase.createOwner(invalidOwner));
    }



    @Test
    public void createOwner_WhenCalledWithEmailTaken_ThrowsException(){
        String takenEmail = "ggeordymm@gmail.com";
        User invalidOwner = validUserOwner;
        invalidOwner.setEmail(takenEmail);

        when(userPersistencePort.existsUserByEmail(takenEmail)).thenReturn(true);


        assertThrows(UserEmailAlreadyTaken.class, () -> userUseCase.createOwner(invalidOwner));
    }

    @Test
    public void createOwner_WhenCalledWithIdentityDocumentTakenThrowsException(){
        Long takenIdentityDocument = 1018522721L;
        User invalidOwner = validUserOwner;
        invalidOwner.setIdentityDocument(takenIdentityDocument);

        when(userPersistencePort.existsUserByIdentityDocument(takenIdentityDocument)).thenReturn(true);


        assertThrows(UserIdentityDocumentAlreadyTaken.class, () -> userUseCase.createOwner(invalidOwner));
    }


    @Test
    public void createClient_WhenCalledWithValidData_DoesNotReturnException(){
        Role roleClient = new Role(RoleEnum.CLIENTE.getId(), RoleEnum.CLIENTE.getName());
        validUserOwner.setRole(roleClient);
        userUseCase.createClient(validUserOwner);

        assertDoesNotThrow(() -> userUseCase.createOwner(validUserOwner));
    }

    @Test
    public void createEmployee_WhenCalledWithValidData_DoesNotReturnException(){
        Role roleEmployee = new Role(RoleEnum.EMPLOYEE.getId(), RoleEnum.EMPLOYEE.getName());
        validUserOwner.setRole(roleEmployee);
        userUseCase.createEmployee(validUserOwner);

        assertDoesNotThrow(() -> userUseCase.createEmployee(validUserOwner));
    }





}
