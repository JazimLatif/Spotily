package com.spotily.app.song;

public interface SongDAO {
    int addNewSong(Song song);

    int deleteSong(int id);

    int editSongDetails(int id, Song song);
}
