package com.practice.test;

import com.practice.LogEntry;
import com.practice.LogParser;
import java.util.List;

public class LogParserTest {
    public static void run() {
        LogParser parser = new LogParser();
        String good = "192.168.1.1 - alice [10/Oct/2026:13:55:36 +0000] \"GET /api/v1/users HTTP/1.1\" 200 1234";
        LogEntry entry = parser.parse(good);
        TestRunner.eq("parses ip", "192.168.1.1", entry.ip());
        TestRunner.eq("parses user", "alice", entry.user());
        TestRunner.eq("parses method", "GET", entry.method());
        TestRunner.eq("parses path", "/api/v1/users", entry.path());
        TestRunner.eq("parses status", 200, entry.status());
        TestRunner.eq("parses bytes", 1234L, entry.bytes());

        List<String> batch = List.of(
            good,
            "THIS LINE IS MALFORMED",
            "192.168.1.2 - bob [10/Oct/2026:13:55:37 +0000] \"POST /api/v1/orders HTTP/1.1\" 201 567"
        );
        List<LogEntry> entries = parser.parseBatch(batch);
        TestRunner.eq("batch skips malformed lines", 2, entries.size());
    }
}
