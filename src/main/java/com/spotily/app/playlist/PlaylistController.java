package com.spotily.app.playlist;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

  //  @GetMapping
  //  public List<Playlist> makePlaylist(){return playlistService.makePlaylist();}

    @GetMapping("/playlist")
    public List<FilterPlaylist> getAllPlaylist(){ return playlistService.getAllPlaylists(); }

    @GetMapping("/playlist/{id}")
    public List<FilterPlaylist> selectPlaylistById(@PathVariable("id") int id){
        return playlistService.selectPlaylistById(id);
    }


    @DeleteMapping("/playlist/{id}")
    public void deletePlaylist(@PathVariable("id") int id){ playlistService.deletePlaylist(id); }

//    these were tests for the individual parts of the makeplaylist method, these mappings will not be used by the user
//    testing can get ids of songs with same mood as answer string - success
//    @GetMapping("{answer}")
//    public ArrayList<Integer> getByMoodTest(@PathVariable("answer") String answer){
//        return playlistService.getByMood(answer);
//    }
//
////    test out making new playlist in database - success
//    @PostMapping("make/{userId}")
//    public int makePlaylistDoesMakeNewTest(@PathVariable("userId") int userId){
//        return playlistService.newPlaylistTest(userId);
//    }
////    test out able to get latest playlist id  - success
//    @GetMapping()
//    public int getMaxPlaylistIdTest(){
//        return playlistService.getMaxPlaylistIdTest();
//    }
//
////    testing ability to make playlist given multiple answers - success
//    @PostMapping("{userId}/{answer1}/{answer2}/{answer3}")
//    public void getSongsGivenMultipleAnswers(@PathVariable("userId") int userId, @PathVariable("answer1") String answer1,
//                                             @PathVariable("answer2") String answer2,
//                                             @PathVariable("answer3") String answer3){
//        ArrayList<String> answersArrayList = new ArrayList<>( List.of(answer1, answer2, answer3) );
//        playlistService.makePlaylist(answersArrayList, userId);
//    }

    @GetMapping("/playlist/user/{id}")
    public Playlist checkIfUserHasPlaylist (@PathVariable("id") int id){
        return playlistService.checkIfUserHasPlaylist(id);
    }

    @GetMapping("playlist/{id}/{songid}")
    public ArrayList<Integer> checkIfSongIsInPlaylist(@PathVariable("id") int id, @PathVariable("songid") int songid) {
        return playlistService.checkIfSongIsInPlaylist(id, songid);
    }

    @GetMapping("playlist/{id}/songs")
    public ArrayList<Integer> checkAllSongInPlaylist(@PathVariable("id") int id) {
        return playlistService.checkAllSongInPlaylist(id);
    }
    @PostMapping("/playlist/{playlistid}/{songid}")
    public void replaceSong(@PathVariable("playlistid") int id,@PathVariable("songid") int songid){
        playlistService.replaceSongInPlaylist(id, songid);
    }
}
