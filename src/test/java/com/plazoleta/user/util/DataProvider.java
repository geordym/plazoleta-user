package com.plazoleta.user.util;


import com.github.javafaker.Faker;
import com.plazoleta.user.domain.model.Role;
import com.plazoleta.user.domain.model.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.plazoleta.user.domain.util.Constants.SECURITY_EMAIL_MIN_LENGTH;
import static com.plazoleta.user.domain.util.Constants.SECURITY_PASSWORD_MIN_LENGTH;

public class DataProvider {

    Faker faker = new Faker();

    public static String getInvalidEmail(){
        return "a".repeat(SECURITY_EMAIL_MIN_LENGTH - 1 );
    }

    public static String getInvalidPassword(){
        return "a".repeat(SECURITY_PASSWORD_MIN_LENGTH - 1 );
    }

    public static String getValidRandomPassword(){
       return Faker.instance().internet().password(SECURITY_PASSWORD_MIN_LENGTH, SECURITY_PASSWORD_MIN_LENGTH + 1);
    }

    public static Stream<String> invalidPasswords() {
        return Stream.of(
                null,
                "",
                ""
        );
    }

    public static Pair<String, String> getPairPasswordNotMatches(){
        return Pair.of("testing4411", "tttesting4");
    }

    public static Pair<String, String> getPairPasswordMatches(){
        return Pair.of("TestingPass123", "TestingPass123");
    }


    public static User getUserValid(){
        User user = new User();
        user.setId(1L);
        user.setRole(new Role(0L, "ROLE"));
        user.setEmail(DataProvider.getValidUsername());
        user.setPassword(DataProvider.getValidRandomPassword());
        return user;
    }

    public static String getSubject(){
        return "testUser";
    }

    public static Map<String, Object> getSimpleTestClaim(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "value");
        return claims;
    }

    public static String getValidUsername(){
        return "a".repeat(SECURITY_EMAIL_MIN_LENGTH + 1);
    }

}
