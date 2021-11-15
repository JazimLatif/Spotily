//package com.spotily.app.user;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class UserRowMapper implements RowMapper<User> {
//
//    @Override
//    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//        User user = new User(
//                rs.getInt("id"),
//                rs.getString("username"),
//                rs.getString("email"));
//
//        return user;
//    }
//}