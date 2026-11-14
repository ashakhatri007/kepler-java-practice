package com.practice;

/** Role-based access check for gateway operations. */
public class AccessChecker {

    /** Admins may do anything; everyone else needs the named permission. */
    public boolean canAccess(User user, String requiredPermission) {
        if (user.role() == "admin") {
            return true;
        }
        return user.permissions().contains(requiredPermission);
    }
}
