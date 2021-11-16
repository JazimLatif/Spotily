package com.spotily.app.playlist;

import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import com.spotily.app.playlist.filterplaylist.FilterPlaylistResultSetExtractor;
import com.spotily.app.playlist.filterplaylist.FilterPlaylistRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PlaylistDataAccessService {

    private JdbcTemplate jdbcTemplate;

    public PlaylistDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
        String sql = """
                SELECT DISTINCT songs.id 
                FROM songs INNER JOIN options 
                ON songs.mood = option_mood 
                WHERE option_text = ?;
                """;
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new PlaylistResultSetExtractor(), answer);
    }


    public ArrayList<Integer> getByMoodAndTheme(String answer, int theme){
        String sql = """
                SELECT DISTINCT songs.id 
                FROM songs INNER JOIN options 
                ON songs.mood = option_mood 
                WHERE option_text = ?
                AND song_theme = ?;
                """;
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new PlaylistResultSetExtractor(), answer, theme);
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

    public Optional<Playlist> checkIfUserHasPlaylist(int id){
        String sql = """
                
                SELECT *
                FROM playlist
                INNER JOIN users
                ON playlist.playlist_user = users.id
                WHERE users.id = ?
                
                """;

        return jdbcTemplate.query(sql, new PlaylistRowMapper(), id)
                .stream()
                .findFirst();
    }


    public int removeSongFromPlaylist(int playlistid, int songid){
        String sql = """
                DELETE FROM playlist_maker
                WHERE playlist_id = ? AND song_id = ?
                """;
        return jdbcTemplate.update(sql, playlistid, songid);
    }

    public ArrayList<Integer> checkIfSongIsInPlaylist (int id, int songid){
        String sql = """
                SELECT song_id
                FROM playlist_maker
                WHERE playlist_id = ? AND song_id = ?;
                
                """;
        return (ArrayList<Integer>)jdbcTemplate.query(sql, new FilterPlaylistResultSetExtractor(), id, songid);
    }

    public ArrayList<Integer> checkAllSongInPlaylist (int id){
        String sql = """
                SELECT song_id
                FROM playlist_maker
                WHERE playlist_id = ?;
                
                """;
        return (ArrayList<Integer>)jdbcTemplate.query(sql, new FilterPlaylistResultSetExtractor(), id);
    }

}
