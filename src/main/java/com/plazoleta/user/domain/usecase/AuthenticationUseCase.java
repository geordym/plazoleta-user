package com.plazoleta.user.domain.usecase;

import com.plazoleta.user.domain.api.IAuthenticationServicePort;
import com.plazoleta.user.domain.exception.InvalidUsernameOrPasswordException;
import com.plazoleta.user.domain.model.User;
import com.plazoleta.user.domain.model.other.AuthToken;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import com.plazoleta.user.domain.spi.security.IPasswordEncoderPort;
import com.plazoleta.user.domain.spi.security.ITokenProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.plazoleta.user.domain.util.Constants.SECURITY_EMAIL_MIN_LENGTH;
import static com.plazoleta.user.domain.util.Constants.SECURITY_PASSWORD_MIN_LENGTH;
import static com.plazoleta.user.domain.util.JwtSecurityConstants.*;

@RequiredArgsConstructor
public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IPasswordEncoderPort passwordEncoderPort;
    private final IUserPersistencePort userPersistencePort;
    private final ITokenProviderPort tokenProviderPort;


    @Override
    public AuthToken authenticateUser(String username, String password) {
        if(username == null || password == null){
            throw new IllegalArgumentException();
        }

        if(username.length() < SECURITY_EMAIL_MIN_LENGTH || password.length() < SECURITY_PASSWORD_MIN_LENGTH){
            throw new IllegalArgumentException();
        }

        User user = userPersistencePort.findByEmail(username).orElseThrow(InvalidUsernameOrPasswordException::new);
        validatePassword(password, user.getPassword());
        Map<String, Object> claims = createClaims(user);

        String accessToken = generateAccessToken(String.valueOf(user.getId()), claims);
        String refreshToken = generateRefreshToken(String.valueOf(user.getId()));

        return new AuthToken(accessToken,refreshToken);
    }

    public void validatePassword(String providedPassword, String storedPassword) {

        if(providedPassword == null || storedPassword == null || providedPassword.isEmpty() || storedPassword.isEmpty()){
            throw new IllegalArgumentException();
        }

        boolean isPasswordValid = passwordEncoderPort.matches(providedPassword, storedPassword);
        if (!isPasswordValid) {
            throw new InvalidUsernameOrPasswordException();
        }
    }


    public Map<String, Object> createClaims(User user){
        if(user == null){
            throw new IllegalArgumentException();
        }

        if(user.getRole() == null || user.getRole().getName() == null || user.getRole().getName().isEmpty() || user.getId() == null){
            throw new IllegalArgumentException();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUBJECT_KEY, String.valueOf(user.getId()));
        claims.put(KEY_ROLE_CLAIM, user.getRole().getName());
        return claims;
    }


    public String generateAccessToken(String subject, Map<String, Object> claims) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expirationAt = issuedAt.plusMinutes(ACCESS_TOKEN_DURATION_MINUTES);
        return tokenProviderPort.generateAccessToken(issuedAt, subject, expirationAt, claims);
    }

    public String generateRefreshToken(String subject) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expirationAt = issuedAt.plusMinutes(REFRESH_TOKEN_DURATION_MINUTES);
        return tokenProviderPort.generateRefreshToken(issuedAt, subject, expirationAt);
    }


}
