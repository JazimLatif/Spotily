package com.spotily.app.playlist;

import com.spotily.app.song.Song;


import java.util.List;
import java.util.Optional;

public interface PlaylistDAO {
    int deletePlaylist (int id);
    List<Playlist> getAllPlaylists();
    Optional<Playlist> selectPlaylistbyId(int id);
    

}
