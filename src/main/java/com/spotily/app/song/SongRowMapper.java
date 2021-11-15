package com.spotily.app.song;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

    public class SongRowMapper implements RowMapper<Song> {

        @Override
        public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
            Song song= new Song(
                    rs.getInt("id"),
                    rs.getString("mood"),
                    rs.getString("artist"),
                    rs.getString("song_name"));

            return song;
        }
}
