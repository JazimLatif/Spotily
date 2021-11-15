package com.spotily.app.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SongService {

    private SongDataAccessService songDataAccessService;

    @Autowired
    public SongService(SongDataAccessService songDataAccessService) {
        this.songDataAccessService = songDataAccessService;
    }


    public void addNewSong(int adminId, Song song) {
        ArrayList<Integer> AdminIds = songDataAccessService.getAdmin();
        if (AdminIds.contains(adminId)) {
            songDataAccessService.addNewSong(song);
        } else {
            throw new UnsupportedOperationException("Only Admins are allowed to add songs");
        }
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
