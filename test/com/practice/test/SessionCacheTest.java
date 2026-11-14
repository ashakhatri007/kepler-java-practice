package com.practice.test;

import com.practice.SessionCache;

public class SessionCacheTest {
    public static void run() {
        FakeClock clock = new FakeClock(1_000_000L);
        SessionCache cache = new SessionCache(clock);
        cache.put("s1", "alice", 60);
        clock.advance(1_000);
        TestRunner.eq("session alive after 1s of a 60s TTL", "alice", cache.get("s1"));
        clock.advance(60_000);
        TestRunner.eq("session expired after its TTL", null, cache.get("s1"));
        TestRunner.eq("unknown session is null", null, cache.get("nope"));
    }
}
