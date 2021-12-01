package com.spotily.app.user.userWithID;

import java.util.Objects;

public class UserWithID {

    private final boolean admin;
    private int id;
    private String username;
    private String email;

    public UserWithID(int id, String username, String email, boolean admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWithID that = (UserWithID) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }

    @Override
    public String toString() {
        return "UserWithID{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
