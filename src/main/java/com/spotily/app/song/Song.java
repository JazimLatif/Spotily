package com.spotily.app.song;

import java.util.Objects;
import java.util.Optional;

public class Song {
    private int id;
    private String mood;
    private String songName;
    private String artist;
    private Optional<Integer> theme;


    public Song( String mood, String songName, String artist, Optional<Integer> theme) {
        this.mood = mood;
        this.songName = songName;
        this.artist = artist;
        this.theme = theme;
    }

    public Optional<Integer> getTheme() {
        return theme;
    }

    public void setTheme(Optional<Integer> theme) {
        this.theme = theme;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
        Song song = (Song) o;
        return id == song.id && Objects.equals(mood, song.mood) && Objects.equals(songName, song.songName) && Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mood, songName, artist);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", mood='" + mood + '\'' +
                ", songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
