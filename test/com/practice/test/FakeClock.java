package com.practice.test;

import com.practice.Clock;

/** Controllable clock for time-dependent tests. */
public class FakeClock implements Clock {
    private long now;

    public FakeClock(long startMillis) {
        this.now = startMillis;
    }

    public void advance(long millis) {
        now += millis;
    }

    @Override
    public long millis() {
        return now;
    }
}
