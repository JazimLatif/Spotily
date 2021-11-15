package com.spotily.app.song;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {
    SongDataAccessService songDataAccessService;

    public SongService(SongDataAccessService songDataAccessService) {
        this.songDataAccessService = songDataAccessService;
    }

    public void addNewSong(Song song) {
        songDataAccessService.addNewSong(song);
    }

    public void deleteSong(int id) {
//        Optional<Song> songOptional = songDataAccessService.deleteSong(id);

//        if(songOptional.isEmpty()){
//            throw new ResourceNotFound("Song not found, field can't be empty");
//        }
        songDataAccessService.deleteSong(id);
    }

    public void editSongDetails(int id, Song song) {

//        Optional<Song> songOptional = songDataAccessService.editSongDetails(id, song);
//
//        if (songOptional.isEmpty()){
//            throw new ResourceNotFound("Song not found, field can't be empty");
//        }
        songDataAccessService.editSongDetails(id, song);
    }
}
