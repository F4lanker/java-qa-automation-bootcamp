# Restful-Booker — API Notes

**Base URL:** `https://restful-booker.herokuapp.com`
**Official docs:** https://restful-booker.herokuapp.com/apidoc/index.html
**Source:** https://github.com/mwinteringham/restful-booker (created by Mark Winteringham as an API testing playground)

## Endpoints

| Method | Path | Auth | Purpose |
|---|---|---|---|
| GET | /ping | none | health check |
| POST | /auth | none | returns `{ "token": "..." }` |
| GET | /booking | none | list booking IDs, supports filters |
| GET | /booking/{id} | none | booking details |
| POST | /booking | none | create booking |
| PUT | /booking/{id} | token | full update |
| PATCH | /booking/{id} | token | partial update |
| DELETE | /booking/{id} | token | delete booking |

## Confirmed quirks

- **`GET /ping` returns `201 Created`, not `200 OK`.** This is the project's actual, intended behavior. It deviates from REST convention — a GET normally returns 200 — but for this API it's the real contract, not a defect.
  - Consequence for tests: assert `201` directly. No `@Disabled` needed — a red test here would mean the assertion is wrong, not the API.
- **Response body for `/ping` is the plain text `"Created"`, not empty and not JSON.** (Correction: an earlier version of this note, based on a third-party source, claimed the body was empty — verified wrong against the live API on 2026-08-04. Trust our own test runs over secondary docs.)
  - Assert with `.body(equalTo("Created"))` (Hamcrest, plain text) — don't reuse a spec that forces `Content-Type: application/json` on this endpoint, and check the actual `Content-Type` header rather than assuming JSON.

## Reported quirks (third-party sources — verify independently before relying on them)
- `PUT /booking/{id}` has been reported to accept malformed `checkin`/`checkout` date strings without validation.
- Filtering `GET /booking` by `checkout` date has been reported as unreliable by other testers.

> These are from other testers' public write-ups, not from official docs or our own verification yet. Treat as hypotheses to confirm with our own negative tests (Task 4), not as established facts to hardcode into assertions.

## Authentication

`POST /auth` with `{ "username": "...", "password": "..." }` returns `{ "token": "..." }`.

For `PUT`/`PATCH`/`DELETE`, the token must be attached — official docs list **two** alternatives:
- `Cookie: token=<token>`
- `Authorization: Basic <base64(admin:password123)>`

**Not confirmed by us yet, reported by another tester (kat-kan, public repo):** the `Authorization: Basic` path returned `403 Forbidden` in their runs — only the `Cookie` header worked reliably. Docs and reality may disagree here. Verify both ourselves in Task 2 before picking one as the default in `authSpec`; don't assume the docs are accurate just because they list two options.

This is a structurally different transport than reqres.in's auth (`X-API-Key` header + Bearer-style token). Not just different field names (`username` vs `email`) — a different mechanism for attaching the token to the request. Relevant for the auth-abstraction architecture decision (see Day 31 chat notes).

## Data behavior
- Pre-loaded with 10 records by default.
- Full reset to the default seed every 10 minutes — don't write tests that assume a specific total record count persists across runs.