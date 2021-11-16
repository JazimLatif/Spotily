package com.spotily.app.user;

import java.util.Optional;

public interface UserDAO {
    int createAccount(User user);
    int createAdminAccount(User user);
    int deleteUser(int id);
    int editUserAccountDetails(int id, User user);
}
