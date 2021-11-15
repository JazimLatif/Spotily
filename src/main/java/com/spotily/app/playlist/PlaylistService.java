package com.spotily.app.playlist;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlaylistService {
    private PlaylistDataAccessService playlistDataAccessService;

    @Autowired
    public PlaylistService(PlaylistDataAccessService playlistDataAccessService) {
        this.playlistDataAccessService = playlistDataAccessService;
    }

//    add user id, comes from the quiz part, check all done in there/from quiz controller
    public void makePlaylist(ArrayList<String> answers, int userId){
//        something to get the mood of each answer, then query the db for that mood and build playlist
//        using int for song ids
        ArrayList<Integer> playlist = new ArrayList<Integer>();
        for (String ans: answers){
            ArrayList<Integer> songsWithMood = getByMood(ans);
//            select random from songswithmood
            int randomSong = songsWithMood.get(new Random().nextInt(songsWithMood.size()));
//            check not already in playlist
            if (!playlist.contains(randomSong)){
                playlist.add(randomSong);
            }

        }
//        actually assign id? or in rowmapping leave that out and let sql handle it...
//        Playlist playlistObj = new Playlist(0, 0, playlist);

//        currently doing one song per answer
//        need to make playlist returning id in makeplaylist
        playlistDataAccessService.makeNewPlaylist(userId);
        int newPlaylistId = playlistDataAccessService.getNewestPlaylistId();
        System.out.println(newPlaylistId);
//        to pass here and then can use to add to playlist_songs join table
        for (int i = 0; i<playlist.size(); i++){
            System.out.println(Integer.toString(playlist.get(i)) +" "+  Integer.toString(newPlaylistId));
            playlistDataAccessService.addToPlaylist(newPlaylistId, playlist.get(i));
        }


    }

//    public void assignUserPlaylist(Playlist playlistObj){
//        playlistDataAccessService.assignPlaylist(playlistObj);
//
//    }

    public ArrayList<Integer> getByMood(String answer){
        ArrayList<Integer> songIds = playlistDataAccessService.getByMood(answer);
        return songIds;
    }


    public List<Playlist> getAllPlaylists(){
        return playlistDataAccessService.getAllPlaylists();
    }

    public Playlist selectPlaylistById(int id) {
        return playlistDataAccessService.selectPlaylistbyId(id)
                .orElseThrow(() -> new ResourceNotFound("Playlist " + id + "does not exist"));
    }


    public void deletePlaylist(int id){
        Optional<Playlist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + "does not exist");
        }
        playlistDataAccessService.deletePlaylist(id);

    }

    public int newPlaylistTest(int userId){
        return playlistDataAccessService.makeNewPlaylist(userId);
    }

    public int getMaxPlaylistIdTest(){
        return playlistDataAccessService.getNewestPlaylistId();
    }

}
