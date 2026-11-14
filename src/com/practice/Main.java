package com.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo driver: parses a small batch of access-log lines, enriches entries with
 * their users, and prints a latency report.
 *
 * Run with bash run.sh (requires Java 17+).
 */
public class Main {

    public static void main(String[] args) {
        List<String> lines = List.of(
            "192.168.1.1 - alice [10/Oct/2026:13:55:36 +0000] \"GET /api/v1/users HTTP/1.1\" 200 1234",
            "192.168.1.2 - bob [10/Oct/2026:13:55:37 +0000] \"POST /api/v1/orders HTTP/1.1\" 201 567",
            "THIS LINE IS MALFORMED",
            "192.168.1.3 - carol [10/Oct/2026:13:55:38 +0000] \"GET /api/v1/users/42 HTTP/1.1\" 500 89"
        );

        LogParser parser = new LogParser();
        List<LogEntry> entries = parser.parseBatch(lines);
        System.out.println("Parsed " + entries.size() + " of " + lines.size() + " lines.");

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            users.add(new User("user-" + i, "User " + i, "user", List.of("read")));
        }
        UserDirectory directory = new UserDirectory(users);

        long start = System.nanoTime();
        int enriched = 0;
        for (int i = 0; i < 20_000; i++) {
            User user = directory.findById("user-" + (i % 100_000));
            if (user != null) {
                enriched++;
            }
        }
        long elapsedMillis = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Enriched " + enriched + " entries in " + elapsedMillis + " ms.");

        List<Long> latencies = List.of(12L, 45L, 33L, 120L, 88L, 200L, 15L, 60L);
        ReportGenerator report = new ReportGenerator();
        System.out.println("Average latency: " + report.averageLatency(latencies) + " ms");
        System.out.println("p95 latency: " + report.percentile95(latencies) + " ms");
        System.out.println("Requests by status: " + report.countByStatus(entries));
    }
}
