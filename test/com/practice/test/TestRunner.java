package com.practice.test;

import java.util.Objects;

/**
 * Tiny test harness (no JUnit dependency). Each suite's run() method calls
 * check/eq; a suite that throws is reported as a failure and the rest still run.
 */
public class TestRunner {
    private static int passed = 0;
    private static int failed = 0;

    public static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("  PASS " + name);
        } else {
            failed++;
            System.out.println("  FAIL " + name);
        }
    }

    public static void eq(String name, Object expected, Object actual) {
        check(name + " (expected=" + expected + ", actual=" + actual + ")",
                Objects.equals(expected, actual));
    }

    private static void runSuite(String name, Runnable suite) {
        System.out.println(name);
        try {
            suite.run();
        } catch (Throwable t) {
            failed++;
            System.out.println("  FAIL " + name + " threw " + t);
        }
    }

    public static void main(String[] args) {
        runSuite("LogParserTest", LogParserTest::run);
        runSuite("RateLimiterTest", RateLimiterTest::run);
        runSuite("SessionCacheTest", SessionCacheTest::run);
        runSuite("AccessCheckerTest", AccessCheckerTest::run);
        runSuite("ReportGeneratorTest", ReportGeneratorTest::run);
        runSuite("UserDirectoryTest", UserDirectoryTest::run);
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
