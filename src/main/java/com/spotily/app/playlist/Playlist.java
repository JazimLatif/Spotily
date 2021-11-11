package com.spotily.app.playlist;

import com.spotily.app.song.Song;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private int userId;
    private List<Integer> songIds;

    public Playlist(int id, int userId, List<Integer> songIds) {
        this.id = id;
        this.userId = userId;
        this.songIds = songIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getSongs() {
        return songIds;
    }

    public void setSongs(List<Integer> songIds) {
        this.songIds = songIds;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", userId=" + userId +
                ", songIds=" + songIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return id == playlist.id && userId == playlist.userId && Objects.equals(songIds, playlist.songIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, songIds);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
