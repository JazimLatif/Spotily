package com.spotily.app.playlist;

import com.spotily.app.exception.ResourceNotFound;
import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import com.spotily.app.song.SongDataAccessService;
import com.spotily.app.user.User;
import com.spotily.app.user.UserDataAccessService;
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

//    add user id, comes from the quiz part, check all done in there/from quiz controller
public void makePlaylist(ArrayList<String> answers, int userId){
//        something to get the mood of each answer, then query the db for that mood and build playlist
//        using int for song ids
    ArrayList<Integer> playlist = new ArrayList<Integer>();

    // delete while loop if you want playlist bigger than 5-6, currently will create a playlist of 5-6 even
    // if you only answer 1 question.
    while(playlist.size() < 5){
            for (String ans : answers) {
                ArrayList<Integer> songsWithMood = getByMood(ans);
//            select random from songswithmood
                int randomSong = songsWithMood.get(new Random().nextInt(songsWithMood.size()));
//            check not already in playlist
                if (!playlist.contains(randomSong)) {
                    playlist.add(randomSong);
                }
            }
        }

//        actually assign id? or in rowmapping leave that out and let sql handle it...
//        Playlist playlistObj = new Playlist(0, 0, playlist);

//        currently doing one song per answer
//        need to make playlist returning id in makeplaylist
        playlistDataAccessService.makeNewPlaylist(userId);
        int newPlaylistId = playlistDataAccessService.getNewestPlaylistId();
        System.out.println(newPlaylistId);
//        to pass here and then can use to add to playlist_songs join table
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(Integer.toString(playlist.get(i)) + " " + Integer.toString(newPlaylistId));
            playlistDataAccessService.addToPlaylist(newPlaylistId, playlist.get(i));
        }
    }

//    public void assignUserPlaylist(Playlist playlistObj){
//        playlistDataAccessService.assignPlaylist(playlistObj);
//
//    }

    public ArrayList<Integer> getByMood(String answer){
        ArrayList<Integer> songIds = playlistDataAccessService.getByMood(answer);
        if(songIds.isEmpty()){
            throw new ResourceNotFound("No " +answer+ " songs found");
        }
        return songIds;
    }


    public List<FilterPlaylist> getAllPlaylists(){
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.getAllPlaylists();
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("No playlists found");
        }
        return playlistOptional;
    }

    public List<FilterPlaylist> selectPlaylistById(int id) {
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + "does not exist");
        }
        return playlistOptional;

    }


    public void deletePlaylist(int id){
        List<FilterPlaylist> playlistOptional = playlistDataAccessService.selectPlaylistbyId(id);
        if(playlistOptional.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + "does not exist");
        }
        playlistDataAccessService.deletePlaylist(id);

    }

    public int newPlaylistTest(int userId){
        Optional<User> optionalUser = userDataAccessService.checkUserExists(userId);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFound("Error: user not found");
        }
        return playlistDataAccessService.makeNewPlaylist(userId);
    }

    public int getMaxPlaylistIdTest(){
        return playlistDataAccessService.getNewestPlaylistId();
    }

    public Playlist checkIfUserHasPlaylist(int id){
        return playlistDataAccessService.checkIfUserHasPlaylist(id)
                .orElseThrow(() -> new ResourceNotFound("User has no playlists"));
    }

    public ArrayList<Integer> checkIfSongIsInPlaylist(int id, int songid){
        ArrayList<Integer> checkSong = playlistDataAccessService.checkIfSongIsInPlaylist(id, songid);
        if(!checkSong.contains(songid)){
            throw new ResourceNotFound("Song " + songid + " is not found in playlist " + id);
        }
        return checkSong;
    }

    public ArrayList<Integer> checkAllSongInPlaylist(int id){
        ArrayList<Integer> checkSongs = playlistDataAccessService.checkAllSongInPlaylist(id);
        if(checkSongs.isEmpty()){
            throw new ResourceNotFound("Playlist " + id + " has no songs");
        }
        return checkSongs;
    }


    public void replaceSongInPlaylist(int playlistid, int songid){
       ArrayList<Integer> checkSong =  checkIfSongIsInPlaylist(playlistid, songid);
       ArrayList<Integer> checkAllSongs = checkAllSongInPlaylist(playlistid);

        if(checkSong.contains(songid)) {
            String mood = songDataAccessService.getSongMood(songid).toString().replace("[", "")
                    .replace("]", "");
            ArrayList<Integer> songsWithMood = getByMood(mood);
            int randomSong = new Random().nextInt(songsWithMood.size());
            while(songid == randomSong && randomSong == 0 && checkAllSongs.contains(randomSong)){
                randomSong = new Random().nextInt(songsWithMood.size());
            }
            if (songid != randomSong && randomSong != 0 && !checkAllSongs.contains(randomSong))  {
                playlistDataAccessService.addToPlaylist(playlistid, randomSong);
                playlistDataAccessService.removeSongFromPlaylist(playlistid, songid);
            } else {
                throw new ResourceNotFound("Please try again");
            }
        }

    }
}
