package br.com.autadesouza.alegriaapi.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer expiresIn;

}
