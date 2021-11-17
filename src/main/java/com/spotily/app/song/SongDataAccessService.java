package com.spotily.app.song;

import com.spotily.app.song.UserSong.UserSong;
import com.spotily.app.song.UserSong.UserSongRowMapper;
import com.spotily.app.user.UserResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongDataAccessService implements SongDAO{

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
                song.getSongName()
        );
    }

    public int addThemedSong(Song song) {
        // admin use
        String sql = """
              INSERT INTO songs(mood, artist, song_name, song_theme)
              VALUES(?, ?, ?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                song.getMood(),
                song.getArtist(),
                song.getSongName(),
                song.getTheme().get()
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
              DELETE FROM songs
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
                song.getSongName(),
                id
        );
    }

    public List<Song> showAdminSongs(Song song) {
        String sql= """
                SELECT mood,song_name,artist,song_theme FROM songs;
                """;
        return jdbcTemplate.query(sql, new SongRowMapper());
    }

    public List<UserSong> showUserSongs(Song song) {
        String sql = """
                SELECT song_name, artist FROM songs;
                """;
        return jdbcTemplate.query(sql, new UserSongRowMapper());
    }

    public Object getSongMood(int id){
        String sql = """
                SELECT mood
                FROM songs
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new SongResultSetExtractor(), id);

    }
}
