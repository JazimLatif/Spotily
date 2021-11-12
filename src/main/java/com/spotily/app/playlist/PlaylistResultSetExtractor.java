package com.spotily.app.playlist;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class PlaylistResultSetExtractor implements ResultSetExtractor {

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
