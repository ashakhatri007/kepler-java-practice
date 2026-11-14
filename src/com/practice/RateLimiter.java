package com.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Per-client token-bucket rate limiter.
 *
 * Each client gets a bucket holding up to {@code capacity} tokens, refilled at
 * {@code refillPerSecond} tokens per second.
 */
public class RateLimiter {

    private static class Bucket {
        double tokens;
        long lastRefillMillis;

        Bucket(double tokens, long now) {
            this.tokens = tokens;
            this.lastRefillMillis = now;
        }
    }

    private final double capacity;
    private final double refillPerSecond;
    private final Clock clock;
    private final Map<String, Bucket> buckets = new HashMap<>();

    public RateLimiter(double capacity, double refillPerSecond, Clock clock) {
        this.capacity = capacity;
        this.refillPerSecond = refillPerSecond;
        this.clock = clock;
    }

    /** Returns true if the client may proceed (consuming one token). */
    public synchronized boolean tryAcquire(String clientId) {
        long now = clock.millis();
        Bucket bucket = buckets.computeIfAbsent(clientId, id -> new Bucket(capacity, now));
        refill(bucket, now);
        if (bucket.tokens >= 1.0) {
            bucket.tokens -= 1.0;
            return true;
        }
        return false;
    }

    private void refill(Bucket bucket, long now) {
        long elapsedMillis = now - bucket.lastRefillMillis;
        long elapsedSeconds = elapsedMillis / 1000;
        bucket.tokens = Math.min(capacity, bucket.tokens + elapsedSeconds * refillPerSecond);
        bucket.lastRefillMillis = now;
    }
}
