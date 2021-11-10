package com.spotily.app.song;

import java.util.Objects;

public class Song {
    private int id;
    private String mood;
    private String songname;
    private String artist;

    public Song(int id, String mood, String songname, String artist) {
        this.id = id;
        this.mood = mood;
        this.songname = songname;
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

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
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
        return id == song.id && Objects.equals(mood, song.mood) && Objects.equals(songname, song.songname) && Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mood, songname, artist);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", mood='" + mood + '\'' +
                ", songname='" + songname + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
