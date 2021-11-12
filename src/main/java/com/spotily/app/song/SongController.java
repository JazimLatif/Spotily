package com.spotily.app.song;

import com.spotily.app.user.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/testspotily")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public void addNewSong(@RequestBody Song song) {
        songService.addNewSong(song);
    }

    @DeleteMapping("{id}")
    public void deleteSong(@PathVariable("id") int id) {
        songService.deleteSong(id);
    }

    @PutMapping("{id}")
    public void editSongDetails(@PathVariable("id") int id, @RequestBody Song song) {
        songService.editSongDetails(id, song);
    }

}
