package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Latency and traffic statistics over parsed log entries. */
public class ReportGenerator {

    /** Mean latency in milliseconds. */
    public double averageLatency(List<Long> latencies) {
        long total = 0;
        for (long latency : latencies) {
            total += latency;
        }
        return total / latencies.size();
    }

    /** 95th-percentile latency (nearest-rank method). */
    public long percentile95(List<Long> latencies) {
        List<Long> sorted = new ArrayList<>(latencies);
        Collections.sort(sorted);
        int index = (int) (0.95 * sorted.size());
        return sorted.get(index);
    }

    /** Counts of log entries per HTTP status code. */
    public Map<Integer, Long> countByStatus(List<LogEntry> entries) {
        return Map.of();
    }
}
