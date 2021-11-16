package com.spotily.app.user;

import com.spotily.app.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addNewUser(User user) {

        userDAO.createAccount(user);
    }

    public void addAdminUser(User user) {

        userDAO.createAdminAccount(user);
    }

    public void deleteUser(int id) {
//         to delete a user delete playlist first due to foreign key
        userDAO.deleteUser(id);

    }

    public void editUserAccountDetails(int id, User user) {

        userDAO.editUserAccountDetails(id, user);
    }



}
