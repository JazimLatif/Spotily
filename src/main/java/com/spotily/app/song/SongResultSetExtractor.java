package com.spotily.app.song;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<String> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<String> songMood = new ArrayList<String>();
        while (rs.next()){
            songMood.add(rs.getString("mood"));
        }
        return songMood;
    }
}