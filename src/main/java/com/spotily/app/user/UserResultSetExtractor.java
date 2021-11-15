package com.spotily.app.user;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserResultSetExtractor implements ResultSetExtractor {
    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> adminIds = new ArrayList<Integer>();
        while (rs.next()){
            adminIds.add(rs.getInt("id"));
        }
        return adminIds;
    }
}
