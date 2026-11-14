package com.practice.test;

import com.practice.AccessChecker;
import com.practice.User;
import java.util.List;

public class AccessCheckerTest {
    public static void run() {
        AccessChecker checker = new AccessChecker();
        // Deliberately not interned: a role string read from a request, not a literal.
        User admin = new User("u1", "Ada", new String("admin"), List.of());
        TestRunner.check("admin may do anything", checker.canAccess(admin, "delete"));
        User reader = new User("u2", "Bob", "user", List.of("read"));
        TestRunner.check("user with permission may proceed", checker.canAccess(reader, "read"));
        TestRunner.check("user without permission is denied", !checker.canAccess(reader, "delete"));
    }
}
