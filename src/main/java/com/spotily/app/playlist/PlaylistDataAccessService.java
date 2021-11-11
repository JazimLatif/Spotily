package com.spotily.app.playlist;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class PlaylistDataAccessService {

    private JdbcTemplate jdbcTemplate;

    public makePlaylist(ArrayList<String> answers){

//         first need to create an empty playlist then use that id to connect it to song_id
//         which is found by filtering by mood of song. Might be easier to remove song_id
//         from playlist_songs and replace it with mood_id.

        String sql = """
                WITH new_playlist AS (
                    INSERT INTO playlists (user_id)
                    VALUES (?)
                    RETURNING id
                )
                WITH mood_filter AS (
                    SELECT id
                    FROM songs
                    WHERE mood = ?
                )
                
                INSERT INTO playlist_songs (playlist_id, song_id)
                VALUE (new_playlist, mood_filter);
                     
                """;
        
        // need to double check above code...
        
        ArrayList<Playlist> newPlaylist = jdbcTemplate.update(sql, )

    }

    public List<Playlist> getAllPlaylists(){
//        sql logic
        String sql = """ 
                SELECT * FROM playlist_songs GROUP BY playlist_id;
                """;
//        come back to this sql query...

        //                SELECT song_name
        //                FROM songs
        //                INNER JOIN playlist_songs
        //                ON playlist_songs.song_id = songs.id
        //                GROUP BY playlist_songs.playlist_id
        List<Playlist> playlistList = jdbcTemplate.query(sql,  new PlaylistRowMapper());
//        add the sql query results to list
        return playlistList;
    }

//    get list of song ids that match the mood indicated by answer
    public ArrayList<Integer> getByMood(String answer){
//        sql query - may be easier to add a mood tag to the answers/options rather than the code
        String sql = """
                SELECT id FROM songs WHERE mood = ?;
                """;
        ArrayList<Integer> songIdList = jdbcTemplate.queryForList(sql, answer);
//        above needs work, get ids from the object map that the query returns
        return songIdList;
    }

    public int deletePlaylist(int id){ return 0;}

    public Optional<Playlist> selectPlaylistbyId(int id){
        String sql = """
                
                SELECT song_name
                FROM songs
                INNER JOIN playlist_songs
                ON playlist_songs.song_id = songs.id
                WHERE playlist_songs.playlist_id = ?
                
                """;

//        return jdbcTemplate.query(sql, new PlaylistRowMapper(), id)
//                .stream()
//                .findFirst();
    }

    public boolean assignPlaylist(Playlist pl){
//        sql insert to add playlist to user, playlist obj has user id inside
//        probably no need to add a playlist in another method, this will both add and assign
        return false;
    }
}
