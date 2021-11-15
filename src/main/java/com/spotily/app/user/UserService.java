package com.spotily.app.user;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {



    private UserDAO userDAO;
    
//    public UserService(@Qualifier("testspotily") UserDAO userDAO) {
//
//        this.userDAO = userDAO;
//    }


    public void addNewUser(User user) {

        userDAO.addNewUser(user);
    }

    public void deleteUser(int id) {
        Optional<User> userOptional = userDAO.deleteUser(id);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFound("User not found");
        }
        userDAO.deleteUser(id);
    }

    public void editUserAccountDetails(int id, User user) {
        Optional<User> userOptional = userDAO.editUserAccountDetails(id, user);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFound("User account not found");
        }
        userDAO.editUserAccountDetails(id, user);
    }

    public void getUserPlaylist(int id) {
        // logic - gets user details and randomly generated playlist
        userDAO.getUserPlaylist(id);
    }

}
