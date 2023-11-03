package com.newjeanssa.budongbudong.config.security.service;

import com.newjeanssa.budongbudong.model.dao.UserDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));
    }

    private UserDetails createUserDetails(UserDto userDto) {
        GrantedAuthority grantedAuthority =
                new SimpleGrantedAuthority(userDto.getAuthority().toString());
        return new User(userDto.getEmail()
                , userDto.getPassword()
                , Collections.singleton(grantedAuthority));
    }
}
