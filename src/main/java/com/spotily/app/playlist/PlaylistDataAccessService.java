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

@Repository("testspotily")
public class PlaylistDataAccessService {

    private JdbcTemplate jdbcTemplate;

    public int makePlaylist(Playlist playlist){


        String sql = """
                WITH new_playlist AS (
                INSERT INTO playlists (user_id)
                VALUES (?)
                RETURNING id
                )
                /*
                WITH mood_filter AS (
                SELECT id
                FROM songs
                INNER JOIN options on option_mood=songs.mood
                WHERE mood='?'
                )
                */

                INSERT INTO playlist_maker (playlist_id, song_id)
                VALUE (new_playlist, mood_filter);

                """;


        return jdbcTemplate.update(
                sql);
//                playlist.getId(),
//                playlist.getUserId(),
//                playlist.getSongs().
//                user.getUsermood());


    }
    public void addToPlaylist(ArrayList<Integer> songId, int playlistId){
        String sql = """
                
                """;

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
                FROM songs INNER JOIN options ON songs.mood = option_mood
                WHERE option_text = ?;
                """;

        //        ResultSet rs = jdbcTemplate.query(sql, answer);
//        above needs work, get ids from the object map that the query returns
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new PlaylistResultSetExtractor());
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

//    public boolean assignPlaylist(Playlist pl){
////        sql insert to add playlist to user, playlist obj has user id inside
////        probably no need to add a playlist in another method, this will both add and assign
//        return false;
//    }
}
