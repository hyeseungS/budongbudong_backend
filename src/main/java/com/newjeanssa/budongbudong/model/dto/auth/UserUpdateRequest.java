package com.newjeanssa.budongbudong.model.dto.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateRequest {
    private String password;
    private String name;
}
