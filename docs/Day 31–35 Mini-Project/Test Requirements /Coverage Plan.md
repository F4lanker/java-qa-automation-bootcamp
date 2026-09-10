# Day 31 — Test Requirements / Coverage Plan

## Task 1 — Smoke & config
- [x] `RestfulBookerConfig` / `restfulBookRequestSpec()` wired into `ApiSpecs`
- [x] `GET /ping` → `201` (matches documented behavior, see `day31-api-notes.md`)
- [ ] Response body assertion — body is the plain text `"Created"`, assert `.body(equalTo("Created"))`
- [ ] Response time assertion (basic SLA, e.g. `< 3000ms`)

## Task 2 — Auth layer — DONE
- [x] `POST /auth` valid credentials → token extracted from JSON body, `Cookie`-based `authSpec` built
- [x] `POST /auth` invalid credentials → asserts both `200` status and `{"reason": "Bad credentials"}` body
- [x] Raw HTTP call extracted to `RestfulBookerAuthApi` (utility, no `@BeforeAll` dependency) — negative tests don't `extend` the provider
- [x] Credentials externalized via `RestfulBookerCredsConfig` (OWNER) reading `pass.properties`

## Cleanup / tech debt (non-blocking, carried forward)
- [ ] Remove `implements RequestSpecification` from `ApiSpecs` (unnecessary, ~150 dead stub methods)
- [ ] Fix `AuthConfig.RESFTFUL_BOOKER_AUTH` — inconsistent spelling, unify to `RESTFUL_BOOKER_AUTH` (or whatever the agreed canonical spelling ends up being) across the project
- [ ] Fix `pass.properties`/`pass.example` comments: `//` → `#`
- [ ] Optional: `Assertions.assertNotNull(response)` in `RestfulBookerAuthApi.authResponseRestulBooker()` is close to a vacuous assertion (RestAssured's `Response` object is effectively never null after `.extract().response()`) — consider dropping it, it doesn't test anything meaningful

## Task 3 — CRUD happy path (current)
- [ ] `POST /booking` → 200, response contains generated `bookingid` + booking payload
- [ ] `GET /booking/{id}` → matches created data
- [ ] `PUT /booking/{id}` (with token) → full update reflected on a subsequent GET

## Task 4 — Negative / error paths (not started, beyond auth negative case)
- [ ] `GET /booking/{id}` with a non-existent id → 404
- [ ] `PUT`/`DELETE` without a token → confirm actual status code (401 vs 403), don't assume
- [ ] Malformed date on create/update → document actual behavior (real bug vs. missing validation) — this is where the `ErrorTestCase` structure decision gets made

## Open questions (his to decide, not mine)
- Dedicated `ErrorTestCase`/`ErrorPathTest` structure — carried over from Day 30. Decide once the negative cases in Task 4 are actually written.
- Final default auth transport for `authSpec`: `Cookie` confirmed working; `Authorization: Basic` reported unreliable by another tester, not yet verified firsthand.