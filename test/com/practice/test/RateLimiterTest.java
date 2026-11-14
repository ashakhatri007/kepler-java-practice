package com.practice.test;

import com.practice.RateLimiter;

public class RateLimiterTest {
    public static void run() {
        FakeClock clock = new FakeClock(1_000_000L);
        RateLimiter limiter = new RateLimiter(10, 10, clock);
        for (int i = 0; i < 10; i++) {
            TestRunner.check("acquire " + (i + 1) + " of 10 succeeds", limiter.tryAcquire("client-a"));
        }
        TestRunner.check("11th acquire is rejected", !limiter.tryAcquire("client-a"));
        clock.advance(500);
        TestRunner.check("half a second refills 5 tokens", limiter.tryAcquire("client-a"));
        clock.advance(500);
        TestRunner.check("a full second refills the bucket", limiter.tryAcquire("client-a"));
        TestRunner.check("other clients have their own bucket", limiter.tryAcquire("client-b"));
    }
}
