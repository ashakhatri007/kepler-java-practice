#!/bin/bash
# Runs the demo driver (Main). Requires Java 17+.
set -e
cd "$(dirname "$0")"
rm -rf out
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.practice.Main
