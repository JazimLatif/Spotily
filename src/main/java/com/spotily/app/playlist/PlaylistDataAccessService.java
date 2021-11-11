package com.spotily.app.playlist;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaylistDataAccessService {

    public List<Playlist> getAllPlaylists(){
//        sql logic
        List<Playlist> emptyList = new ArrayList<Playlist>();
        return emptyList;
    }

//    get list of song ids that match the mood indicated by answer
    public ArrayList<Integer> getByMood(String answer){
//        sql query - may be easier to add a mood tag to the answers/options rather than the code
        ArrayList<Integer> songIdList = new ArrayList<Integer>();
//        might be a better data type
        return songIdList;
    }

    public int deletePlaylist(int id){ return 0;}
}
