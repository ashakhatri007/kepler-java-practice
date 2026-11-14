package com.practice.test;

import com.practice.LogEntry;
import com.practice.ReportGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportGeneratorTest {
    public static void run() {
        ReportGenerator report = new ReportGenerator();
        TestRunner.eq("average is not truncated", 100.5, report.averageLatency(List.of(100L, 101L)));
        List<Long> oneToHundred = new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            oneToHundred.add(i);
        }
        TestRunner.eq("p95 of 1..100 is 95", 95L, report.percentile95(oneToHundred));
        List<LogEntry> entries = List.of(
            new LogEntry("1.1.1.1", "a", "GET", "/x", 200, 10),
            new LogEntry("1.1.1.2", "b", "GET", "/y", 200, 10),
            new LogEntry("1.1.1.3", "c", "GET", "/z", 500, 10)
        );
        Map<Integer, Long> counts = report.countByStatus(entries);
        TestRunner.eq("counts 200s", 2L, counts.get(200));
        TestRunner.eq("counts 500s", 1L, counts.get(500));
    }
}
