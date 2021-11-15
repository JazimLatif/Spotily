package com.spotily.app.playlist.filterplaylist;

import java.util.Objects;

public class FilterPlaylist {
    private int playlist;
    private String song_name;
    private  String artist;

    public FilterPlaylist(int playlist, String song_name, String artist) {
        this.playlist = playlist;
        this.song_name = song_name;
        this.artist = artist;
    }

    public int getPlaylist() {
        return playlist;
    }

    public void setPlaylist(int playlist) {
        this.playlist = playlist;
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
        FilterPlaylist maker = (FilterPlaylist) o;
        return playlist == maker.playlist && Objects.equals(song_name, maker.song_name) && Objects.equals(artist, maker.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, song_name, artist);
    }

    @Override
    public String toString() {
        return "Maker{" +
                "playlist=" + playlist +
                ", song_name='" + song_name + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
