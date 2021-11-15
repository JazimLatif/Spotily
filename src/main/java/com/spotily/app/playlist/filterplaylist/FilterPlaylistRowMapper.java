package com.spotily.app.playlist.filterplaylist;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FilterPlaylistRowMapper implements RowMapper<FilterPlaylist> {

    @Override
    public FilterPlaylist mapRow(ResultSet rs, int rowNum) throws SQLException {
        FilterPlaylist filterPlaylist = new FilterPlaylist(
                rs.getInt("playlist_id"),
                rs.getString("song_name"),
                rs.getString("artist"));
        return filterPlaylist;
    }
}
