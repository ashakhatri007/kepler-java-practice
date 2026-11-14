package com.practice;

import java.util.HashMap;
import java.util.Map;

/** In-memory session store. Sessions expire {@code ttlSeconds} after creation. */
public class SessionCache {

    private record Session(String userId, long expiresAtMillis) {}

    private final Clock clock;
    private final Map<String, Session> sessions = new HashMap<>();

    public SessionCache(Clock clock) {
        this.clock = clock;
    }

    public void put(String sessionId, String userId, long ttlSeconds) {
        long expiresAt = clock.millis() + ttlSeconds;
        sessions.put(sessionId, new Session(userId, expiresAt));
    }

    /** Returns the user id for a live session, or null if absent or expired. */
    public String get(String sessionId) {
        Session session = sessions.get(sessionId);
        if (session == null || clock.millis() >= session.expiresAtMillis()) {
            sessions.remove(sessionId);
            return null;
        }
        return session.userId();
    }
}
