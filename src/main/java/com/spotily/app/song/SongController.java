package com.spotily.app.song;

import com.spotily.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }
    @GetMapping("/showSongs/{adminId}")
    public List<Song> showSongs(@PathVariable("adminId") int adminId, Song song){
        return (List<Song>) songService.showSongs(adminId, song);
    }

    @PostMapping("addSong/{adminId}")
    public void addNewSong(@PathVariable("adminId") int adminId, @RequestBody Song song) {
        songService.addNewSong(adminId, song);
    }

    @DeleteMapping("/delete/{adminId}/{id}")
    public void deleteSong(@PathVariable("adminId") int adminId, @PathVariable("id") int id) {
        songService.deleteSong(adminId, id);
    }

    @PutMapping("/update/{adminId}/{id}")
    public void editSongDetails(@PathVariable("adminId") int adminId, @PathVariable("id") int id, @RequestBody Song song) {
        songService.editSongDetails(adminId, id, song);
    }

    @GetMapping("/mood/{id}")
    public Object getSongMood(@PathVariable("id") int id){
        return songService.getSongMood(id);
    }

}
