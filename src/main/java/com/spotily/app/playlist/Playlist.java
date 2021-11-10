package com.spotily.app.playlist;

import com.spotily.app.song.Song;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private List<Song> songs;

    public Playlist(int id, List<Song> songs) {
        this.id = id;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return id == playlist.id && Objects.equals(songs, playlist.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songs);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", songs=" + songs +
                '}';
    }
}
