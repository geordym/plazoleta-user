package com.plazoleta.user.domain.model.other;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    private String accessToken;
    private String refreshToken;
  }
