package com.spotily.app.user.userWithID;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserWithID> {

    @Override
    public UserWithID mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserWithID user = new UserWithID(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getBoolean("admin"));

        return user;
    }
}
