package com.spotily.app.playlist;


import java.util.ArrayList;
import java.util.List;

import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/playlist")
    public List<FilterPlaylist> getAllPlaylist(){ return playlistService.getAllPlaylists(); }

    @GetMapping("/playlist/{id}")
    public List<FilterPlaylist> selectPlaylistById(@PathVariable("id") int id){
        return playlistService.selectPlaylistById(id);
    }


    @DeleteMapping("/playlist/{id}")
    public void deletePlaylist(@PathVariable("id") int id){ playlistService.deletePlaylist(id); }

    @GetMapping("{mood}")
    public ArrayList<Integer> getByMoodTest(@PathVariable("mood") String mood){
        return playlistService.getByMood(mood);
    }
}
