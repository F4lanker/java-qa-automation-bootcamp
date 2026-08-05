# Day 31 — Test Requirements / Coverage Plan

## Task 1 — Smoke & config
- [x] `RestfulBookerConfig` / `rstflBookerReqSpec()` wired into `ApiSpecs`
- [x] `GET /ping` → `201` (matches documented behavior, see `day31-api-notes.md`)
- [ ] Response body assertion (expect empty body)
- [ ] Response time assertion (basic SLA, e.g. `< 3000ms` — public sandbox, keep the threshold generous)

## Task 2 — Auth layer
- [ ] `POST /auth` with valid credentials → token returned
- [ ] `POST /auth` with invalid credentials → documented failure response
- [ ] Architecture decision recorded in the PR description: inheritance (`extends`) vs. composition for how auth is applied to authenticated tests

## Task 3 — CRUD happy path
- [ ] `POST /booking` → 200, response contains generated `bookingid` + booking payload
- [ ] `GET /booking/{id}` → matches created data
- [ ] `PUT /booking/{id}` (with token) → full update reflected on a subsequent GET

## Task 4 — Negative / error paths
- [ ] `GET /booking/{id}` with a non-existent id → 404
- [ ] `PUT`/`DELETE` without a token → confirm actual status code (401 vs 403), don't assume
- [ ] Malformed date on create/update → document actual behavior (real bug vs. missing validation) — this is where the `ErrorTestCase` structure decision gets made

## Open questions
- Dedicated `ErrorTestCase`/`ErrorPathTest` structure — carried over from Day 30. Decide once the negative cases in Task 4 are actually written, not before.