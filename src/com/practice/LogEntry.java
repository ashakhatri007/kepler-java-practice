package com.practice;

/** One parsed access-log line. */
public record LogEntry(String ip, String user, String method, String path, int status, long bytes) {}
