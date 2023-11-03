package com.newjeanssa.budongbudong.model.dto.auth;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignInRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
