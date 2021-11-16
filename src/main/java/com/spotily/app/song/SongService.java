package com.spotily.app.song;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteSong(int adminId, int id) {
        ArrayList<Integer> AdminIds = songDataAccessService.getAdmin();
        if (AdminIds.contains(adminId)) {
            songDataAccessService.deleteSong(id);
        } else {
            throw new UnsupportedOperationException("Only Admins are allowed to delete songs");
        }
    }

    public void editSongDetails(int adminId, int id, Song song) {
        ArrayList<Integer> AdminIds = songDataAccessService.getAdmin();
        if (AdminIds.contains(adminId)) {
            songDataAccessService.editSongDetails(id, song);
        } else {
            throw new UnsupportedOperationException("Only Admins are allowed to delete songs");
        }
    }

    public List<? extends Object> showSongs(int adminId, Song song) {
        ArrayList<Integer> AdminIds = songDataAccessService.getAdmin();
        if (AdminIds.contains(adminId)) {
            return songDataAccessService.showAdminSongs(song);
        } else  {
            return songDataAccessService.showUserSongs(song);
        }
    }

    public Object getSongMood(int id){
        Object songMood = songDataAccessService.getSongMood(id);
        if(songMood.equals(null)){
            throw new ResourceNotFound("Song" + id + "doesn't exist");
        }
        return songMood;
    }
}
