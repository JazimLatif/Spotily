package com.spotily.app.playlist.filterplaylist;

import java.util.Objects;

public class FilterPlaylist {
    private int playlist;
    private int userid;
    private int song_id;
    private String song_name;
    private  String artist;

    public FilterPlaylist(int playlist, int userid, int song_id, String song_name, String artist) {
        this.playlist = playlist;
        this.userid = userid;
        this.song_id = song_id;
        this.song_name = song_name;
        this.artist = artist;
    }

    public int getPlaylist() {
        return playlist;
    }

    public void setPlaylist(int playlist) {
        this.playlist = playlist;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterPlaylist that = (FilterPlaylist) o;
        return playlist == that.playlist && userid == that.userid && song_id == that.song_id && Objects.equals(song_name, that.song_name) && Objects.equals(artist, that.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, userid, song_id, song_name, artist);
    }

    @Override
    public String toString() {
        return "FilterPlaylist{" +
                "playlist=" + playlist +
                ", userid=" + userid +
                ", song_id=" + song_id +
                ", song_name='" + song_name + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
