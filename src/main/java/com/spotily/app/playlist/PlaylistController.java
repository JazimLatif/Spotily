package com.spotily.app.playlist;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaylistController {

    private PlaylistService playlistService;

    @GetMapping
    public List<Playlist> getAllPlaylist(){ return playlistService.getAllPlaylists(); }

    @GetMapping("{id}")
    public Optional<Playlist> selectPlaylistById(@PathVariable("id") int id){
        return playlistService.selectPlaylistById(id);
    }


    @DeleteMapping("{id}")
    public void deletePlaylist(@PathVariable("id") int id){ playlistService.deletePlaylist(id); }
}
