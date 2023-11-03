package com.newjeanssa.budongbudong.model.service.auth;

import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.config.security.config.SecurityUtil;
import com.newjeanssa.budongbudong.config.security.jwt.TokenDto;
import com.newjeanssa.budongbudong.config.security.jwt.TokenProvider;
import com.newjeanssa.budongbudong.model.dao.UserDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignInRequest;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;


    /*
    회원가입
     */
    @Transactional
    public void signUp(UserSignUpRequest userSignUpRequest) {
        if(userDao.findByEmail(userSignUpRequest.getEmail()).isPresent()){
            throw new BaseException(EXIST_ACCOUNT);
        }
        UserDto userDto = UserDto.builder()
                .email(userSignUpRequest.getEmail())
                .name(userSignUpRequest.getName())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .build();
        userDao.save(userDto);
    }

    /*
    로그인
     */
    public TokenDto signIn(UserSignInRequest userSignInRequest) {
        return authenticationUser(userSignInRequest.getEmail(), userSignInRequest.getPassword());
    }


    /*
    재발급
     */
    public TokenDto reissue(String refreshToken, HttpServletRequest request) throws IOException {
        // refreshToken 만료 시
        if (refreshToken == null) {
            throw new BaseException(EXPIRED_REFRESH);
        }

        // 기존 accessToken 찾기
        String accessToken = tokenProvider.resolveToken(request);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);

        // 토큰 발급
        return generateToken(authentication);
    }

    /*
    사용자 검증
     */
    public TokenDto authenticationUser(String principal, String credentials) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                principal, credentials
        );
        return generateToken(authenticationManagerBuilder.getObject().authenticate(authenticationToken));
    }


    /*
    토큰 생성
     */
    public TokenDto generateToken(Authentication authentication) {
        return tokenProvider.generateTokenDto(authentication);
    }

    /*
    로그아웃
     */
    public void logout(HttpServletResponse response) {

        // 쿠키 삭제
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", null)
                .maxAge(0)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();

        response.setHeader("Set-Cookie", cookie.toString());
    }

    /*
    사용자 아이디
     */
    public String getUserEmail() {
        return SecurityUtil.getCurrentMemberId();
    }

    /*
    이메일로 사용자 정보 조회
     */
    public Optional<UserDto> getUserByEmail(String email) { return userDao.findByEmail(email); }
}
