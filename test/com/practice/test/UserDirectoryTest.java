package com.practice.test;

import com.practice.User;
import com.practice.UserDirectory;
import java.util.List;

public class UserDirectoryTest {
    public static void run() {
        User ada = new User("u1", "Ada", "admin", List.of());
        User bob = new User("u2", "Bob", "user", List.of("read"));
        UserDirectory directory = new UserDirectory(List.of(ada, bob));
        TestRunner.eq("finds existing user", ada, directory.findById("u1"));
        TestRunner.eq("missing user is null", null, directory.findById("u9"));
    }
}
