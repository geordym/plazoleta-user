package com.plazoleta.user.domain.spi;

public interface IPasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
