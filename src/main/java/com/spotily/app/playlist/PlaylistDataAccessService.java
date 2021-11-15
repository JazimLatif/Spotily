package com.spotily.app.playlist;

import com.spotily.app.user.User;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistDataAccessService {

    private JdbcTemplate jdbcTemplate;

    public PlaylistDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

// insert returning id, will initially get max id rather than returning
    public int makeNewPlaylist(int userId){

        String sql = """
                INSERT INTO playlist (playlist_user)
                VALUES (?);
                """;

        return jdbcTemplate.update(sql, userId);


    }

    public int getNewestPlaylistId(){
        String sql = """
                SELECT id FROM playlist ORDER BY id DESC LIMIT 1;
                """;
        ArrayList<Integer> playlistIdArray = (ArrayList<Integer>) jdbcTemplate.query(sql, new PlaylistResultSetExtractor());
        return playlistIdArray.get(0);
    }

    public int addToPlaylist(Integer songId, int playlistId){
        String sql = """
                INSERT INTO playlist_maker
                (playlist_id, song_id)
                VALUES (?, ?);
                """;
//        loop thru songIds in playlistservice and insert each into db with same playlist id passed above from makeplaylist
        return jdbcTemplate.update(sql, songId, playlistId);
    }

    public List<Playlist> getAllPlaylists(){
//        sql logic
        String sql = """ 
                SELECT * FROM playlist_maker GROUP BY playlist_id;
                """;
//        come back to this sql query...

        //                SELECT song_name, artist
        //                FROM songs
        //                INNER JOIN playlist_maker
        //                ON playlist_maker.song_id = songs.id
        //                GROUP BY playlist_maker.playlist_id
        List<Playlist> playlistList = jdbcTemplate.query(sql,  new PlaylistRowMapper());
//        add the sql query results to list
        return playlistList;
    }
//    get list of song ids that match the mood indicated by answer
    public ArrayList<Integer> getByMood(String answer){
//        sql query - may be easier to add a mood tag to the answers/options rather than the code
        String sql = """
                SELECT DISTINCT songs.id 
                FROM songs INNER JOIN options 
                ON songs.mood = option_mood 
                WHERE option_text = ?;
                """;

        //        ResultSet rs = jdbcTemplate.query(sql, answer);
//        above needs work, get ids from the object map that the query returns
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new PlaylistResultSetExtractor(), answer);
    }

    public ArrayList<Integer> getByMoodAndTheme(String answer, int theme){
//        sql query - may be easier to add a mood tag to the answers/options rather than the code
        String sql = """
                SELECT DISTINCT songs.id 
                FROM songs INNER JOIN options 
                ON songs.mood = option_mood 
                WHERE option_text = ?
                AND song_theme = ?;
                """;

        //        ResultSet rs = jdbcTemplate.query(sql, answer);
//        above needs work, get ids from the object map that the query returns
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new PlaylistResultSetExtractor(), answer, theme);
    }

    public int deletePlaylist(int id){ return 0;}

    public Optional<Playlist> selectPlaylistbyId(int id){
        String sql = """
                
                SELECT song_name, artist
                FROM songs
                INNER JOIN playlist_maker
                ON playlist_maker.song_id = songs.id
                WHERE playlist_maker.playlist_id = ?
                
                """;

        return jdbcTemplate.query(sql, new PlaylistRowMapper(), id)
                .stream()
                .findFirst();
    }

}
