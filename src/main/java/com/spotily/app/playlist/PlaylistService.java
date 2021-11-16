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
        playlistDataAccessService.makeNewPlaylist(userId);
        int newPlaylistId = playlistDataAccessService.getNewestPlaylistId();

        for (int i = 0; i<playlist.size(); i++){
            System.out.println(Integer.toString(playlist.get(i)) +" "+  Integer.toString(newPlaylistId));
            playlistDataAccessService.addToPlaylist(newPlaylistId, playlist.get(i));
        }


    }

    public void makeThemedPlaylist(ArrayList<String> answers, int userId, int theme){
        ArrayList<Integer> playlist = new ArrayList<Integer>();
        for (String ans: answers){
            ArrayList<Integer> songsWithMood = getByMoodAndTheme(ans, theme);
            int randomSong = songsWithMood.get(new Random().nextInt(songsWithMood.size()));
            if (!playlist.contains(randomSong)){
                playlist.add(randomSong);
            }
        }
        playlistDataAccessService.makeNewPlaylist(userId);
        int newPlaylistId = playlistDataAccessService.getNewestPlaylistId();
        for (int i = 0; i<playlist.size(); i++){
//            System.out.println(Integer.toString(playlist.get(i)) +" "+  Integer.toString(newPlaylistId));
            playlistDataAccessService.addToPlaylist(newPlaylistId, playlist.get(i));
        }

    }

    public ArrayList<Integer> getByMood(String answer){
        ArrayList<Integer> songIds = playlistDataAccessService.getByMood(answer);
        if(songIds.isEmpty()){
            throw new ResourceNotFound("No " +answer+ " songs found");
        }
        return songIds;
    }

    public ArrayList<Integer> getByMoodAndTheme(String answer, int theme){
        ArrayList<Integer> songIds = playlistDataAccessService.getByMoodAndTheme(answer, theme);
        if(songIds.isEmpty()){
            throw new ResourceNotFound("No " +answer+ " songs found");
        }
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

    public int newPlaylistTest(int userId){
        return playlistDataAccessService.makeNewPlaylist(userId);
    }

    public int getMaxPlaylistIdTest(){
        return playlistDataAccessService.getNewestPlaylistId();
    }

}
