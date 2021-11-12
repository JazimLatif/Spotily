package com.spotily.app.user;

import com.spotily.app.song.Song;
import com.spotily.app.song.SongRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testspotily")
public class UserDataAccessService  {
    JdbcTemplate jdbcTemplate;

    public int addNewUser(User user) {
        String sql = """
                INSERT INTO users(username, email)
                VALUES(?,?);
                """;
        return jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword()
        );

    }

    public int deleteUser(int id) {
        String sql= """
                REMOVE FROM users
                WHERE id=?
                """;
        return jdbcTemplate.update(sql,id);
    }

    public int editUserAccountDetails(int id, User user) {
        String sql= """
                UPDATE TABLE users(username, email)
                SET username=?,email=?
                WHERE ID = ?;
                """;
        return jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getEmail(),
                user.getId()
        );

    }

    public List<Song> getUserPlaylist(int id) {

    String sql= """
                SELECT * FROM playlist
                WHERE id=?;
                """;
        return jdbcTemplate.query(sql,new SongRowMapper(),id);

    }
}
