package com.spotily.app.playlist;

import com.spotily.app.exception.ResourceNotFound;
import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import com.spotily.app.song.SongDataAccessService;
import com.spotily.app.user.User;
import com.spotily.app.user.UserDataAccessService;
import com.spotily.app.user.userWithID.UserWithID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlaylistService {
    private PlaylistDataAccessService playlistDataAccessService;
    private UserDataAccessService userDataAccessService;
    private SongDataAccessService songDataAccessService;

    @Autowired
    public PlaylistService(PlaylistDataAccessService playlistDataAccessService,
                           UserDataAccessService userDataAccessService,
                           SongDataAccessService songDataAccessService) {
        this.playlistDataAccessService = playlistDataAccessService;
        this.userDataAccessService = userDataAccessService;
        this.songDataAccessService = songDataAccessService;
    }

    public void makeNewPlaylist(int userId){
        playlistDataAccessService.makeNewPlaylist(userId);
    }

    public int getNewestPlaylistId(){
        return playlistDataAccessService.getNewestPlaylistId();
    }

    public void addToPlaylist(int userId, int songId){
        playlistDataAccessService.addToPlaylist(userId, songId);
    }


    public void makePlaylist(ArrayList<String> answers, int userId) {
        ArrayList<Integer> playlist = new ArrayList<Integer>();
        answers.addAll(answers);
//        iterates through each quiz answer and does the business logic
        for (String ans : answers) {
//            calls get
            ArrayList<Integer> songsWithMood = getByMood(ans);
//            select a random song from the arraylist returned by the function
            int randomSong = songsWithMood.get(new Random().nextInt(songsWithMood.size()));
//            check not already in playlist
            if (!playlist.contains(randomSong)) {
//                adds otherwise
                playlist.add(randomSong);
            }
        }
        makeNewPlaylist(userId);
        int newPlaylistId = getNewestPlaylistId();

        for (int i = 0; i<playlist.size(); i++){
            addToPlaylist(newPlaylistId, playlist.get(i));
        }

    }

    public void makeThemedPlaylist(ArrayList<String> answers, int userId, int theme) {
        ArrayList<Integer> playlist = new ArrayList<Integer>();
        answers.addAll(answers);
        for (String ans : answers) {
            ArrayList<Integer> songsWithMood = getByMoodAndTheme(ans, theme);
            int randomSong = songsWithMood.get(new Random().nextInt(songsWithMood.size()));
            if (!playlist.contains(randomSong)) {
                playlist.add(randomSong);
            }
        }
        makeNewPlaylist(userId);
        int newPlaylistId = getNewestPlaylistId();
        for (int i = 0; i<playlist.size(); i++){
            addToPlaylist(newPlaylistId, playlist.get(i));
        }

    }

    public ArrayList<Integer> getByMood(String answer) {
        ArrayList<Integer> songIds = playlistDataAccessService.getByMood(answer);
        if (songIds.isEmpty()) {
            throw new ResourceNotFound("No " + answer + " songs found");
        }
        return songIds;
    }

    public ArrayList<Integer> getByMoodAndTheme(String answer, int theme) {
        ArrayList<Integer> songIds = playlistDataAccessService.getByMoodAndTheme(answer, theme);
        if (songIds.isEmpty()) {
            throw new ResourceNotFound("No " + answer + " songs found");
        }
        return songIds;
    }

    public List<FilterPlaylist> getAllPlaylists() {
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.getAllPlaylists();
        if (playlistOptional.isEmpty()) {
            throw new ResourceNotFound("No playlists found");
        }
        return playlistOptional;
    }

    public List<FilterPlaylist> selectPlaylistById(int id) {
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + " does not exist");

        }
        return playlistDataAccessService.selectPlaylistbyId(id);

    }

    public void deletePlaylist(int id) {
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);

        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + " does not exist");
        }
        playlistDataAccessService.deletePlaylist(id);
    }

    public int newPlaylistTest(int userId) {
        Optional<UserWithID> optionalUser = userDataAccessService.checkUserExists(userId);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFound("Error: user not found");
        }
        return playlistDataAccessService.makeNewPlaylist(userId);
    }

    public int getMaxPlaylistIdTest() {
        return playlistDataAccessService.getNewestPlaylistId();
    }

    public Playlist checkIfUserHasPlaylist(int id) {
        return playlistDataAccessService.checkIfUserHasPlaylist(id)
                .orElseThrow(() -> new ResourceNotFound("User has no playlists"));
    }

    public ArrayList<Integer> checkIfSongIsInPlaylist(int id, int songid) {
        ArrayList<Integer> checkSong = playlistDataAccessService.checkIfSongIsInPlaylist(id, songid);
        if (!checkSong.contains(songid)) {
            throw new ResourceNotFound("Song " + songid + " is not found in playlist " + id);
        }
        return checkSong;
    }

    public ArrayList<Integer> checkAllSongInPlaylist(int id) {
        ArrayList<Integer> checkSongs = playlistDataAccessService.checkAllSongInPlaylist(id);
        if (checkSongs.isEmpty()) {
            throw new ResourceNotFound("Playlist " + id + " has no songs");
        }
        return checkSongs;
    }


    public void replaceSongInPlaylist(int playlistid, int songid) {
        ArrayList<Integer> checkSong = checkIfSongIsInPlaylist(playlistid, songid);
        ArrayList<Integer> checkAllSongs = checkAllSongInPlaylist(playlistid);

        if (checkSong.contains(songid)) {
            String mood = songDataAccessService.getSongMood(songid).toString().replace("[", "")
                    .replace("]", "");
            ArrayList<Integer> songsWithMood = getByMood(mood);
            int randomNumber = new Random().nextInt(songsWithMood.size());
            int randomSong = songsWithMood.get(randomNumber);
            while (songid == randomSong || randomSong == 0 || checkAllSongs.contains(randomSong)) {
                randomSong = new Random().nextInt(songsWithMood.size());
            }
                playlistDataAccessService.addToPlaylist(playlistid, randomSong);
                playlistDataAccessService.removeSongFromPlaylist(playlistid, songid);
        }

    }
}
