package com.spotily.app.song;

import com.spotily.app.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/song")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public void addNewSong(@RequestBody Song song) {
        songService.addNewSong(song);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSong(@PathVariable("id") int id) {
        songService.deleteSong(id);
    }

    @PutMapping("/put/{id}")
    public void editSongDetails(@PathVariable("id") int id, @RequestBody Song song) {
        songService.editSongDetails(id, song);
    }

    @GetMapping("/mood/{id}")
    public Object getSongMood(@PathVariable("id") int id){
        return songService.getSongMood(id);
    }
}
