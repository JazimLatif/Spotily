package com.spotily.app.user;

import com.spotily.app.playlist.Playlist;

public interface UserDAO {
    int addNewUser(User user);
    int deleteUser(int id);
    int editUserAccountDetails(int id, User user);
    Playlist getUserPlaylist(int id);

}
