package com.newjeanssa.budongbudong.model.dto.auth;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Role.class)
public class RoleTypeHandler implements TypeHandler<Role> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Role role, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, role.getKey());
    }

    @Override
    public Role getResult(ResultSet resultSet, String s) throws SQLException {
        String roleKey = resultSet.getString(s);
        return getRole(roleKey);
    }

    @Override
    public Role getResult(ResultSet resultSet, int i) throws SQLException {
        String roleKey = resultSet.getString(i);
        return getRole(roleKey);
    }

    @Override
    public Role getResult(CallableStatement callableStatement, int i) throws SQLException {
        String roleKey = callableStatement.getString(i);
        return getRole(roleKey);
    }

    private Role getRole(String roleKey) {
        Role role = null;
        switch (roleKey) {
            case "ROLE_USER" :
                role = Role.USER;
                break;
            default:
                role = Role.ADMIN;
                break;
        }
        return role;
    }
}