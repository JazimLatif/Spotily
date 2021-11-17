package com.spotily.app.song;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SongRowMapper implements RowMapper<Song> {

        @Override
        public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
            Song song= new Song(
                    rs.getString("mood"),
                    rs.getString("song_name"),
                    rs.getString("artist"),
                    Optional.of(rs.getInt("song_theme")));

            return song;
        }


}
