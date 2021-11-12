package com.spotily.app.song;

import java.util.Optional;

public interface SongDAO {
    int addNewSong(Song song);

    Optional<Song> deleteSong(int id);

    Optional<Song> editSongDetails(int id, Song song);
}
