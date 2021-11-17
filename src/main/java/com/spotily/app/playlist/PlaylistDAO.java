package com.spotily.app.playlist;

import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import com.spotily.app.song.Song;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PlaylistDAO {
    int deletePlaylist (int id);
    List<FilterPlaylist> getAllPlaylists();
    List<FilterPlaylist> selectPlaylistbyId(int id);
    int makeNewPlaylist(int userId);
    int getNewestPlaylistId();
    int addToPlaylist(Integer songId, int playlistId);
    ArrayList<Integer> getByMood(String answer);
    ArrayList<Integer> getByMoodAndTheme(String answer, int theme);


}
