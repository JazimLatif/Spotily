package com.spotily.app.user;

import com.spotily.app.playlist.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addNewUser(User user) {
        // logic
        userDAO.addNewUser(user);
    }

    public void deleteUser(int id) {
        // logic
        userDAO.deleteUser(id);
    }

    public void editUserAccountDetails(int id, User user) {
        // logic - Optional for user without an id
        userDAO.editUserAccountDetails(id, user);
    }

    public Playlist getUserPlaylist(int id) {
        // logic - gets user details and randomly generated playlist
        return userDAO.getUserPlaylist(id);
    }

}
