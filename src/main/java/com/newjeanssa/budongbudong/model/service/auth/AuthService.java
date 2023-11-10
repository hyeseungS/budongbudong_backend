package com.newjeanssa.budongbudong.model.service.auth;

import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.config.security.jwt.JwtTokenProvider;
import com.newjeanssa.budongbudong.config.security.jwt.TokenDto;
import com.newjeanssa.budongbudong.config.security.service.RedisService;
import com.newjeanssa.budongbudong.model.dao.UserDao;
import com.newjeanssa.budongbudong.model.dto.auth.Role;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignInRequest;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final BCryptPasswordEncoder passwordEncoder;

    private final String SERVER = "Server";


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
                .role(Role.USER)
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
    재발급 : validate 메서드가 true 반환할 때만 사용 -> AT, RT 재발급
     */
    @Transactional
    public TokenDto reissue(HttpServletRequest request, String requestRefreshToken) {
        // "Bearer {AT}"에서 {AT} 추출
        String requestAccessToken = jwtTokenProvider.resolveToken(request);

        // AT로 이메일 추출
        Authentication authentication = jwtTokenProvider.getAuthentication(requestAccessToken);
        String principal = jwtTokenProvider.getPrincipal(requestAccessToken);

        // Redis에 "RT(Server) {email}"로 된 정보 있는 지 확인
        String refreshTokenInRedis = redisService.getValues("RT(" + SERVER + "):" + principal);

        if (refreshTokenInRedis == null) { // Redis에 저장되어 있는 RT가 없을 경우
            return null; // -> 재로그인 요청
        }

        // 요청된 RT의 유효성 검사 & Redis에 저장되어 있는 RT와 같은지 비교
        if(!jwtTokenProvider.validateRefreshToken(requestRefreshToken) || !refreshTokenInRedis.equals(requestRefreshToken)) {
            redisService.deleteValues("RT(" + SERVER + "):" + principal); // 탈취 가능성 -> 삭제
            return null; // -> 재로그인 요청
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = getAuthorities(authentication);

        // 토큰 재발급 및 Redis 업데이트
        redisService.deleteValues("RT(" + SERVER + "):" + principal); // 기존 RT 삭제
        TokenDto tokenDto = jwtTokenProvider.createToken(principal, authorities);
        saveRefreshToken(SERVER, principal, tokenDto.getRefreshToken());
        return tokenDto;
    }

    /*
     AT가 만료일자만 초과한 유효한 토큰인지 검사 -> 만료일자만 초과한 경우 재발급 필효
     */
    public boolean validate(HttpServletRequest request) {
        String requestAccessToken = jwtTokenProvider.resolveToken(request);
        return jwtTokenProvider.validateAccessTokenOnlyExpired(requestAccessToken); // true = 재발급
    }

    // 사용자 검증
    public TokenDto authenticationUser(String principal, String credentials) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                principal, credentials
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return generateToken(SERVER, authentication.getName(), getAuthorities(authentication));
    }


    // 토큰 생성
    @Transactional
    public TokenDto generateToken(String provider, String email, String authorities) {
        // RT가 이미 있을 경우
        if(redisService.getValues("RT(" + provider + "):" + email) != null) {
            redisService.deleteValues("RT(" + provider + "):" + email); // 삭제
        }

        // AT, RT 생성 및 Redis에 RT 저장
        TokenDto tokenDto = jwtTokenProvider.createToken(email, authorities);
        saveRefreshToken(provider, email, tokenDto.getRefreshToken());
        return tokenDto;
    }

    // RT를 Redis에 저장
    @Transactional
    public void saveRefreshToken(String provider, String principal, String refreshToken) {
        redisService.setValuesWithTimeout("RT(" + provider + "):" + principal, // key
                refreshToken, // value
                jwtTokenProvider.getTokenExpirationTime(refreshToken)); // timeout(milliseconds)
    }

    // 권한 이름 가져오기
    public String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
