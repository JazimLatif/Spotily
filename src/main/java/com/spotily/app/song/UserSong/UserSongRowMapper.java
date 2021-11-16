package com.spotily.app.song.UserSong;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSongRowMapper implements RowMapper<UserSong> {

    @Override
    public UserSong mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserSong song= new UserSong(
                rs.getString("artist"),
                rs.getString("song_name"));

        return song;
    }


}
