# kepler-java-practice

A 45-minute AI-assisted code-comprehension exercise, modeled on Google's Kepler
interview round: an unfamiliar multi-file Java codebase, an agent (`agy`) in the
terminal, and a clock.

## The codebase

A small API-gateway support service. It parses access logs, rate-limits clients,
caches sessions, checks access, and reports latency.

| File | What it does |
| --- | --- |
| `LogParser` | Parses access-log lines into `LogEntry` records |
| `RateLimiter` | Per-client token-bucket rate limiting |
| `SessionCache` | In-memory session store with TTL expiry |
| `AccessChecker` | Role-based access check (admins bypass permission checks) |
| `ReportGenerator` | Latency stats (average, p95) and per-status counts |
| `UserDirectory` | Looks users up by id |
| `Main` | Demo driver: parses sample logs, enriches entries, prints a report |

Plain Java 17. No dependencies, no build tool, no framework. Everything compiles
with `javac`.

## The exercise

Work in four phases, like the real round.

**Phase 1 — Explore (~10 min). No edits.**
Ask the agent to explain the repo before touching anything. Good prompts:
- "Explain how this repository parses input rows and where malformed input is handled."
- "How does the rate limiter decide whether a request is allowed?"
- "What does the session cache use as its expiry, and in what units?"

**Phase 2 — Fix.**
`bash run-tests.sh` fails. There are bugs hiding in the code. Find them and fix them
until the whole suite passes. Review every diff the agent proposes before
accepting it, and make sure you can explain each fix.

**Phase 3 — Extend.**
`ReportGenerator.countByStatus` is stubbed out and its tests are already written.
Implement it.

**Phase 4 — Optimize.**
`bash run.sh` prints how long enriching 20,000 log entries takes against 100,000
users. Make it fast, and measure before and after.

## Build and test

Requires Java 17 or newer.

- `bash run-tests.sh` — compiles everything and runs the test suite. Exits non-zero
  while any test fails.
- `bash run.sh` — runs the demo driver (`Main`).

## Working with the agent

- Direct it incrementally. One bug at a time beats "fix everything."
- Use `/plan` before a multi-file change, and read the plan.
- `y` accepts a diff, `n` declines it, `f` shows the full diff.
- `@` references a file in a prompt; `!` runs a shell command directly.
- Esc stops the agent mid-run. `/rewind` undoes a turn that went sideways.
