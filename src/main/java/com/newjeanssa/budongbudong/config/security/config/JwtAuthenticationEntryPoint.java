package com.newjeanssa.budongbudong.config.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newjeanssa.budongbudong.common.BaseResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.UN_AUTHORIZED;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper objectMapper = new ObjectMapper();
        String errorJson = objectMapper.writeValueAsString(new BaseResponse<>(UN_AUTHORIZED));
        response.getWriter().print(errorJson);
    }
}
