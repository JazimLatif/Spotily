package com.spotily.app.song;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Service
public class SongService {

    private SongDAO songDAO;

    public void addNewSong(Song song) {
        songDAO.addNewSong(song);
    }

    public void deleteSong(int id) {
        Optional<Song> songOptional = songDAO.deleteSong(id);

        if(songOptional.isEmpty()){
            throw new ResourceNotFound("Song not found, field can't be empty");
        }
        songDAO.deleteSong(id);
    }

    public void editSongDetails(int id, Song song) {

        Optional<Song> songOptional = songDAO.editSongDetails(id, song);

        if (songOptional.isEmpty()){
            throw new ResourceNotFound("Song not found, field can't be empty");
        }
        songDAO.editSongDetails(id, song);
    }
}
