package com.spotily.app.song;

import com.spotily.app.song.UserSong.UserSong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SongDAO {

    int addNewSong(Song song);
    ArrayList<Integer> getAdmin();
    int deleteSong(int id);
    int editSongDetails(int id, Song song);
    List<Song> showAdminSongs(Song song);
    List<UserSong> showUserSongs(Song song);
    int addThemedSong(Song song);

}
