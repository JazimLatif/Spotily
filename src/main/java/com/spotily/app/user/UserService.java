package com.spotily.app.user;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {



    private UserDataAccessService userDataAccessService;
    
    public UserService(UserDataAccessService userDataAccessService) {

        this.userDataAccessService = userDataAccessService;
    }


    public void addNewUser(User user) {

        userDataAccessService.addNewUser(user);
    }

    public void deleteUser(int id) {
//        Optional<User> userOptional = userDataAccessService.deleteUser(id);
//
//        if (userOptional.isEmpty()) {
//            throw new ResourceNotFound("User not found");
//        }
        userDataAccessService.deleteUser(id);
    }

    public void editUserAccountDetails(int id, User user) {
//        Optional<User> userOptional = userDataAccessService.editUserAccountDetails(id, user);
//
//        if (userOptional.isEmpty()) {
//            throw new ResourceNotFound("User account not found");
//        }
        userDataAccessService.editUserAccountDetails(id, user);
    }

    public void getUserPlaylist(int id) {
        // logic - gets user details and randomly generated playlist
        userDataAccessService.getUserPlaylist(id);
    }

}
