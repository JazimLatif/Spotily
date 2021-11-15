package com.spotily.app.song;

import com.spotily.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/song")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("addSong/{adminId}")
    public void addNewSong(@PathVariable("adminId") int adminId, @RequestBody Song song) {
        songService.addNewSong(adminId, song);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSong(@PathVariable("id") int id) {
        songService.deleteSong(id);
    }

    @PutMapping("/put/{id}")
    public void editSongDetails(@PathVariable("id") int id, @RequestBody Song song) {
        songService.editSongDetails(id, song);
    }

}
