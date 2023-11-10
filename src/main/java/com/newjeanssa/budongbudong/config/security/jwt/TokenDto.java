package com.newjeanssa.budongbudong.config.security.jwt;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class TokenDto {

    private String accessToken;

    private String refreshToken;

}
