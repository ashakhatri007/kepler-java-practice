#!/bin/bash
# Compiles the sources and the tests, then runs the test suite.
# Exits non-zero while any test fails.
set -e
cd "$(dirname "$0")"
rm -rf out
mkdir -p out
javac -d out $(find src test -name "*.java")
java -cp out com.practice.test.TestRunner
