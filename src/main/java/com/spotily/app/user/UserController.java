package com.spotily.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

//    @PutMapping("/put/{id}")
//    public void editUserAccountDetails(@PathVariable("id") int id, @RequestBody User user) {
//        userService.editUserAccountDetails(id, user);
//    }

    @GetMapping("{id}")
    public void getUserPlaylist(@PathVariable("id") int id) {
        userService.getUserPlaylist(id);
    }

}
