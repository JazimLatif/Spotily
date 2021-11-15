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

//
    public void makePlaylist(ArrayList<String> answers, int userId){
        ArrayList<Integer> playlist = new ArrayList<Integer>();
//        iterates through each quiz answer and does the business logic
        for (String ans: answers){
//            calls get
            ArrayList<Integer> songsWithMood = getByMood(ans);
//            select a random song from the arraylist returned by the function
            int randomSong = songsWithMood.get(new Random().nextInt(songsWithMood.size()));
//            check not already in playlist
            if (!playlist.contains(randomSong)){
//                adds otherwise
                playlist.add(randomSong);
            }
        }

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
        if(songIds.isEmpty()){
            throw new ResourceNotFound("No " +answer+ " songs found");
        }
        return songIds;
    }


    public List<Playlist> getAllPlaylists(){return playlistDataAccessService.getAllPlaylists();}

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
