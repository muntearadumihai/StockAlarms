package com.devm8.stockalarms.mapper;

import com.devm8.stockalarms.model.UserDO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<UserDO> {

    @Override
    public UserDO mapRow(ResultSet resultSet, int i) throws SQLException {

        return new UserDO.UserDOBuilder().userId(resultSet.getInt("user_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .password(resultSet.getString("password"))
                .email(resultSet.getString("email"))
                .build();
    }
}

