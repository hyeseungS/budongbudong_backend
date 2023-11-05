package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserDao {

    void save(UserDto userDto);
    Optional<UserDto> findByEmail(String email);
    void update(UserDto userDto);
    void delete(String email);

}