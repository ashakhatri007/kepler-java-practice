package com.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses access-log lines of the form:
 *   192.168.1.1 - alice [10/Oct/2026:13:55:36 +0000] "GET /api/v1/users HTTP/1.1" 200 1234
 *
 * Fields (space separated): ip, "-", user, "[date:time", "zone]", "\"METHOD",
 * path, "HTTP/x.y\"", status, bytes.
 */
public class LogParser {

    /** Parses a single line. Returns null if the line is malformed. */
    public LogEntry parse(String line) {
        String[] parts = line.split(" ");
        String ip = parts[0];
        String user = parts[2];
        String method = parts[5].substring(1); // strip leading quote
        String path = parts[6];
        int status = Integer.parseInt(parts[8]);
        long bytes = Long.parseLong(parts[9]);
        return new LogEntry(ip, user, method, path, status, bytes);
    }

    /** Parses a batch of lines, skipping malformed ones. */
    public List<LogEntry> parseBatch(List<String> lines) {
        List<LogEntry> entries = new ArrayList<>();
        for (String line : lines) {
            LogEntry entry = parse(line);
            if (entry != null) {
                entries.add(entry);
            }
        }
        return entries;
    }
}
