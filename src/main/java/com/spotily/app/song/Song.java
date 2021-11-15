package com.spotily.app.song;

import java.util.Objects;

public class Song {
    private int id;
    private String mood;
    private String songName;
    private String artist;

    public Song(int id, String mood, String songName, String artist) {
        this.id = id;
        this.mood = mood;
        this.songName = songName;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
