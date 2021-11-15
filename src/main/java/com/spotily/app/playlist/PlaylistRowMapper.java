package com.spotily.app.playlist;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistRowMapper implements RowMapper<Playlist> {

    @Override 
    public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Playlist playlist = new Playlist(
                rs.getInt("id"),
                rs.getInt("playlist_user"),
                new ArrayList<Integer>()
        );
        return playlist;
    }
}

