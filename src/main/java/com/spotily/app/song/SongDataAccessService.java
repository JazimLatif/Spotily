package com.spotily.app.song;

import com.spotily.app.user.UserResultSetExtractor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class SongDataAccessService{

    private JdbcTemplate jdbcTemplate;

    public SongDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addNewSong(Song song) {
        // admin use
        String sql = """
              INSERT INTO songs(mood, artist, song_name)
              VALUES(?, ?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                song.getMood(),
                song.getArtist(),
                song.getSongname()
        );
    }

    public ArrayList<Integer> getAdmin() {
        String sql = """
                SELECT users.id
                FROM users 
                WHERE users.admin = 'true';
                """;
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new UserResultSetExtractor());
    }

    public int deleteSong(int id) {
        // can add name for deletion in future
        String sql = """
              DELETE FROM song
              WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    public int editSongDetails(int id, Song song) {
        String sql = """
                UPDATE song
                SET mood = ?, artist = ?, song_name = ?
                WHERE id = ?
                  """;
        return jdbcTemplate.update(
                sql,
                song.getMood(),
                song.getArtist(),
                song.getSongname(),
                id
        );
    }

}
