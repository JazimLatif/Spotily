package com.spotily.app.song;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("testspotily")
public interface SongDAO {
    int addNewSong(Song song);

    Optional<Song> deleteSong(int id);

    Optional<Song> editSongDetails(int id, Song song);
}
