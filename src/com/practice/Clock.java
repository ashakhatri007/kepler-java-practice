package com.practice;

/** Minimal clock abstraction so time-dependent code is testable. */
public interface Clock {
    long millis();

    static Clock system() {
        return System::currentTimeMillis;
    }
}
