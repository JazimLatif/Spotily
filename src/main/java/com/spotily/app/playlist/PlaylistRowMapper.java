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

// may have to rethink, but the resultset needs to include all songids where will be used to create a playlist object
//                maybe a separate queryforlist(sql, Integer.class) and then ad to the playlist object which starts
//                with empty list
//                can have that in the constructor for now and think about how whether to change
        );
        return playlist;
    }
}
//private int id;
//    private int userId;
//    private List<Integer> songIds;
