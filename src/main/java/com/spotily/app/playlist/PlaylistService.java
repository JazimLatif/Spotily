package com.spotily.app.playlist;

import com.spotily.app.exception.ResourceNotFound;
import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlaylistService {
    private PlaylistDataAccessService playlistDataAccessService;

    @Autowired
    public PlaylistService(PlaylistDataAccessService playlistDataAccessService) {
        this.playlistDataAccessService = playlistDataAccessService;
    }

    public void makePlaylist(ArrayList<String> answers){
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
        Playlist playlistObj = new Playlist(0, 0, playlist);

//        currently doing one song per answer
        playlistDataAccessService.makePlaylist(playlistObj);

    }

//    public void assignUserPlaylist(Playlist playlistObj){
//        playlistDataAccessService.assignPlaylist(playlistObj);
//
//    }

    public ArrayList<Integer> getByMood(String answer){
        ArrayList<Integer> songIds = playlistDataAccessService.getByMood(answer);
        return songIds;
    }


    public List<FilterPlaylist> getAllPlaylists(){
        return playlistDataAccessService.getAllPlaylists();
    }

    public List<FilterPlaylist> selectPlaylistById(int id) {
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + "does not exist");
        }
        return playlistDataAccessService.selectPlaylistbyId(id);

    }


    public void deletePlaylist(int id){
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + "does not exist");
        }
        playlistDataAccessService.deletePlaylist(id);

    }

}
