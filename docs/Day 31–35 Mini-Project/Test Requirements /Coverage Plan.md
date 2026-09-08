# Day 31 — Test Requirements / Coverage Plan

_Status refreshed after a 3-week break — see chat for full reasoning behind each item._

## Task 1 — Smoke & config
- [x] `RestfulBookerConfig` / `restfulBookRequestSpec()` wired into `ApiSpecs`
- [x] `GET /ping` → `201` (matches documented behavior, see `day31-api-notes.md`)
- [ ] Response body assertion — **note:** body is the plain text `"Created"`, not empty as originally assumed; assert `.body(equalTo("Created"))`
- [ ] Response time assertion (basic SLA, e.g. `< 3000ms` — public sandbox, keep the threshold generous)

## Task 2 — Auth layer (in progress)
- [x] `POST /auth` with valid credentials → token returned (fixed: extract via `jsonPath().getString("token")`, not `.cookie()` — token comes back in the JSON body, not a `Set-Cookie` header; password corrected to `password123`)
- [x] `authRequestSpecCookie(baseUrl, token)` added to `ApiSpecs` — builds the outgoing `Cookie: token=...` header correctly
- [ ] `POST /auth` with invalid credentials → assert **both** `200` status and `{"reason": "Bad credentials"}` body (API returns 200 on bad creds, not 401 — a status-only assert would pass vacuously)
- [ ] Extract the raw auth HTTP call into a standalone utility class (not the `@BeforeAll`-bearing provider), so negative-credential tests don't `extends AuthProviderRestfulBooker` and don't trigger its precondition
- [ ] Finish wiring `pass.properties` credentials into the provider via a config interface (OWNER pattern, matching `ApiKeyConfig`) — keys exist (`rstBookerName`/`rstBookerPass`), not yet read into the class (still hardcoded fields)
- [ ] Fix accumulated code-quality items: remove `ApiSpecs implements RequestSpecification` (unnecessary, ~150 dead stub methods); unify `RestfulBooker` spelling across class/constant names (found: `Restfull`, `Restrull`, `RESFTFUL`, `RESFULL`); fix `.properties` file comments (`//` → `#`)
- [ ] Negative auth test method currently `static` — JUnit5 `@Test` methods can't be static, remove the modifier

## Task 3 — CRUD happy path (not started)
- [ ] `POST /booking` → 200, response contains generated `bookingid` + booking payload
- [ ] `GET /booking/{id}` → matches created data
- [ ] `PUT /booking/{id}` (with token) → full update reflected on a subsequent GET

## Task 4 — Negative / error paths (not started, beyond auth negative case above)
- [ ] `GET /booking/{id}` with a non-existent id → 404
- [ ] `PUT`/`DELETE` without a token → confirm actual status code (401 vs 403), don't assume
- [ ] Malformed date on create/update → document actual behavior (real bug vs. missing validation) — this is where the `ErrorTestCase` structure decision gets made

## Open questions (his to decide, not mine)
- Dedicated `ErrorTestCase`/`ErrorPathTest` structure — carried over from Day 30. Decide once the negative cases in Task 4 are actually written, not before.
- Where the extracted auth utility class lives (package/name) and whether `.properties` config uses OWNER or plain `java.util.Properties`.
- Final default auth transport for `authSpec`: `Cookie` is confirmed working; `Authorization: Basic` is reported unreliable by another tester but not yet verified firsthand — worth confirming empirically before deciding whether to support both.