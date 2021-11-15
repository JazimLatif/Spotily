package com.spotily.app.playlist;

import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import com.spotily.app.playlist.filterplaylist.FilterPlaylistRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<FilterPlaylist> getAllPlaylists(){
        String sql = """ 
                SELECT *                                                         
                FROM songs                                                                      
                INNER JOIN playlist_maker                                                       
                ON songs.id = playlist_maker.song_id
                ORDER BY playlist_id
                 
                """;

        List<FilterPlaylist> playlistList = jdbcTemplate.query(sql,  new FilterPlaylistRowMapper());
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



    public int deletePlaylist(int id){
        String sql = """
                DELETE FROM playlist_maker
                WHERE playlist_id = ?            
                """;
        jdbcTemplate.update(sql, id);

        String sql2 = """
                
                DELETE FROM playlist
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql2, id);
    }


    public List<FilterPlaylist> selectPlaylistbyId(int id){
        String sql = """
                
                SELECT *                                                         
                FROM songs                                                                      
                INNER JOIN playlist_maker                                                       
                ON songs.id = playlist_maker.song_id                                            
                WHERE playlist_id = ?
                
                """;

        return jdbcTemplate.query(sql, new FilterPlaylistRowMapper(), id)
                .stream()
                .collect(Collectors.toList());
    }

//    public boolean assignPlaylist(Playlist pl){
////        sql insert to add playlist to user, playlist obj has user id inside
////        probably no need to add a playlist in another method, this will both add and assign
//        return false;
//    }
}

//    public int makePlaylist(ArrayList<Integer> playlist){
//
//
//        String sql = """
//                WITH new_playlist AS (
//                INSERT INTO playlists (user_id)
//                VALUES (?)
//                RETURNING id
//                )
//                /*
//                WITH mood_filter AS (
//                SELECT id
//                FROM songs
//                INNER JOIN options on option_mood=songs.mood
//                WHERE mood='?'
//                )
//                */
//
//                INSERT INTO playlist_maker (playlist_id, song_id)
//                VALUE (new_playlist, mood_filter);
//
//                """;
//
//
//        return jdbcTemplate.update(
//                sql);
////                playlist.getId(),
////                playlist.getUserId(),
////                playlist.getSongs().
////                user.getUsermood());