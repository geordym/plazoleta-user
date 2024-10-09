package com.plazoleta.user.domain.usecase;


import com.plazoleta.user.domain.exception.InvalidEmailException;
import com.plazoleta.user.domain.exception.InvalidPhoneNumberException;
import com.plazoleta.user.domain.exception.MinorAgeException;
import com.plazoleta.user.domain.model.User;
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

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {


    @Mock
    private IUserPersistencePort userPersistencePort;

    private UserUseCase userUseCase;
    private UserUseCaseValidator userUseCaseValidator;

    private User validUserOwner;

    @BeforeEach
    void setup(){
        userUseCaseValidator = new UserUseCaseValidator();
        userUseCase = new UserUseCase(userPersistencePort, userUseCaseValidator);

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



}
