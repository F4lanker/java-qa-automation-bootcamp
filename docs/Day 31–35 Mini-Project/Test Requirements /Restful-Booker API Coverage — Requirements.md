# Day 31–35 Mini-Project: Restful-Booker API Coverage — Requirements

## Goal
Full test coverage of the Restful-Booker API (https://restful-booker.herokuapp.com), demonstrating:
- REST Assured framework usage (RequestSpec/ResponseSpec, filters, Allure integration)
- OOP design decisions in test architecture (inheritance vs. composition for auth)
- Assertions that catch real API defects, not just happy-path checks

## Scope

### In scope
- Health check (`GET /ping`)
- Authentication (`POST /auth`)
- Booking CRUD: create, read, update (full + partial), delete
- Negative scenarios: not-found, invalid auth, malformed payloads
- Documented API quirks and known bugs (see `day31-api-notes.md`)

### Out of scope
- Load/performance testing beyond basic response-time smoke assertions
- UI testing
- Security/penetration testing

## Constraints
- Public sandbox instance — no control over uptime or data isolation between test runs.
- Data resets to a default seed (10 records) every 10 minutes — tests must not assume record count persists or grows monotonically.
- No official OpenAPI/Swagger spec for this API — contract is derived from `apidoc` documentation and observed behavior. Any schema validation is written manually, not generated from a spec file.

## Definition of Done (per task — see `day31-test-plan.md`)
- Test passes against actual (verified) API behavior, not assumed behavior.
- Assertions include a clear failure message / context (what the assert means, not just what it checks).
- Deviations from REST convention or genuine API bugs are documented explicitly, not silently special-cased.