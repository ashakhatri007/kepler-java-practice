package com.practice;

import java.util.List;

/** Lookup of users by id. Backed by the list loaded at startup. */
public class UserDirectory {

    private final List<User> users;

    public UserDirectory(List<User> users) {
        this.users = users;
    }

    /** Returns the user with the given id, or null if there is none. */
    public User findById(String id) {
        for (User user : users) {
            if (user.id().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
