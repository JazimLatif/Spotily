package com.spotily.app.quiz;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> songIds = new ArrayList<Integer>();
        while (rs.next()){
            songIds.add(rs.getInt("id"));
        }
        return songIds;
    }
}