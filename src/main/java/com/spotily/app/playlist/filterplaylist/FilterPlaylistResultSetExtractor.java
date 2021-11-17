package com.spotily.app.playlist.filterplaylist;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilterPlaylistResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> songId = new ArrayList<Integer>();
        while (rs.next()){
            songId.add(rs.getInt("song_id"));
        }
        return songId;
    }
}