package com.spotily.app.user;

import com.spotily.app.user.userWithID.UserWithID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void createAccount(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @PostMapping("register/admin")
    public void createAdminAccount(@RequestBody User user) {
        userService.addAdminUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public void editUserAccountDetails(@PathVariable("id") int id, @RequestBody User user) {
        userService.editUserAccountDetails(id, user);
    }

    @GetMapping("/allUsers")
    public List<UserWithID> getAllUsers() {
        return userService.getAllUsers();
    }



}
