package com.spotily.app.playlist;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaylistController {

    private PlaylistService playlistService;
//    private PlaylistDataAccessService playlistDataAccessService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

  //  @GetMapping
  //  public List<Playlist> makePlaylist(){return playlistService.makePlaylist();}

    //@GetMapping
    //public List<Playlist> getAllPlaylist(){ return playlistService.getAllPlaylists(); }

//    @GetMapping("{id}")
//    public Playlist selectPlaylistById(@PathVariable("id") int id){
//        return playlistService.selectPlaylistById(id);
//    }


    @DeleteMapping("{id}")
    public void deletePlaylist(@PathVariable("id") int id){ playlistService.deletePlaylist(id); }

    @GetMapping("{mood}")
    public ArrayList<Integer> getByMoodTest(@PathVariable("mood") String mood){
        return playlistService.getByMood(mood);
    }
}
