package com.spotily.app.user;

import com.spotily.app.playlist.Playlist;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDAO {
    int addNewUser(User user);
    Optional<User> deleteUser(int id);
    Optional<User> editUserAccountDetails(int id, User user);
    Playlist getUserPlaylist(int id);

}
