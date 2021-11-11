package com.spotily.app.song;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {

    private SongDAO songDAO;

    public void addNewSong(Song song) {
        songDAO.addNewSong(song);
    }

    public void deleteSong(int id) {
        Optional<Song> songOptional = songDAO.deleteSong(id);



        songDAO.deleteSong(id);
    }

    public void editSongDetails(int id, Song song) {
        songDAO.editSongDetails(id, song);
    }
}
