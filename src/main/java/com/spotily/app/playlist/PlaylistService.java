package com.spotily.app.playlist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlaylistService {
    private PlaylistDataAccessService playlistDataAccessService;

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
//        currently doing one song per answer

    }

    public ArrayList<Integer> getByMood(String answer){
        ArrayList<Integer> songIds = playlistDataAccessService.getByMood(answer);
        return songIds;
    }


    public List<Playlist> getAllPlaylists(){
        return playlistDataAccessService.getAllPlaylists();
    }

//    public List<Playlist> selectPlaylistById(int id) {return playlistDataAccessService.selectPlaylistbyId}


    public void deletePlaylist(int id){
        playlistDataAccessService.deletePlaylist(id);

    }
}
