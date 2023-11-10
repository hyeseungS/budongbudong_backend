package com.newjeanssa.budongbudong.model.service.auth;

import com.newjeanssa.budongbudong.config.security.config.SecurityUtil;
import com.newjeanssa.budongbudong.config.security.jwt.JwtTokenProvider;
import com.newjeanssa.budongbudong.config.security.service.RedisService;
import com.newjeanssa.budongbudong.model.dao.UserDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.auth.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final String SERVER = "Server";

    /*
    로그아웃
     */
    @Transactional
    public void logout(HttpServletRequest request) {
        String requestAccessToken = jwtTokenProvider.resolveToken(request);
        String principal = jwtTokenProvider.getPrincipal(requestAccessToken);

        // Redis에 저장되어 있는 RT 삭제
        String refreshTokenInRedis = redisService.getValues("RT(" + SERVER + "):" + principal);
        if (refreshTokenInRedis != null) {
            redisService.deleteValues("RT(" + SERVER + "):" + principal);
        }

        // Redis에 로그아웃 처리한 AT 저장
        long expiration = jwtTokenProvider.getTokenExpirationTime(requestAccessToken) - new Date().getTime();
        redisService.setValuesWithTimeout(requestAccessToken,
                "logout",
                expiration);
    }


    /*
    사용자 정보 조회
     */
    public Optional<UserDto> getUser() { return userDao.findByEmail(getUserEmail()); }

    /*
    회원정보 수정
     */
    @Transactional
    public void updateUser(UserUpdateRequest userUpdateRequest) {
        UserDto userDto = UserDto.builder()
                .email(userUpdateRequest.getEmail())
                .name(userUpdateRequest.getName())
                .password(passwordEncoder.encode(userUpdateRequest.getPassword()))
                .build();
        userDao.update(userDto);
    }

    /*
    회원정보 삭제
     */
    @Transactional
    public void deleteUser() {
        userDao.delete(getUserEmail());
    }

    // 로그인된 사용자 아이디 조회
    public String getUserEmail() {
        return SecurityUtil.getCurrentMemberId();
    }

}
