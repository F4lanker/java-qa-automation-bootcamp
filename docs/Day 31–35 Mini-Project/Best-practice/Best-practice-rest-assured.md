# Testing Approach & Architecture — Established Practices

_Principles that emerged while building the Restful-Booker mini-project (Days 31+). Written to generalize beyond this one API — revisit when starting the next mini-project or a new API suite._

## Package responsibility split

Not organized by domain alone ("everything about booking together") — organized by responsibility first, domain second:

- `dto.<api>.request` / `dto.<api>.response` — data only, no behavior. Kept as **separate classes per direction**, even when currently structurally identical (see "Request vs Response DTOs" below).
- `api.<api>` — raw HTTP calls to a resource. One utility class per resource (`RestfulBookerAuthApi`, `RestfulBookerBookingApi`). Static methods, private constructor, `AssertionError` guard.
- `util` — infrastructure not tied to a specific resource (config reading, serialization helpers). If a class in `util` is actually "a raw call to endpoint X," it belongs in `api`, not `util` — this drifted once already (`RestfulBookerAuthApi` started in `util` before the split existed).
- `testdata.<api>` — Test Data Builders (below).
- `base` — classes providing JUnit lifecycle preconditions (`@BeforeAll`) for tests that `extend` them.

Rule of thumb for a new helper method: does it hold data with no behavior → `dto`; does it call an endpoint → `api`; is it infrastructure independent of any one endpoint → `util`.

## Raw API-call utilities

- One static method per operation, returning `Response` — **no assertions inside**. The same raw call has to serve both a happy-path test (expects 200) and a negative test (expects 400/404/etc.) — baking in an expected status code defeats that reuse. (Found and flagged: `RestfulBookerBookingApi.bookingApi()` currently asserts `200` internally — scheduled for a fix.)
- Naming mirrors the HTTP operation, not the resource alone: `createBooking`, `getBooking`, `updateBooking` — not a single generic `bookingApi` for everything, which stops being descriptive the moment a second method is added to the same class.
- `@BeforeAll`-based preconditions (`AuthProviderRestfulBooker`) stay separate from these raw-call utilities. A provider class assumes a lifecycle contract (must run before dependent tests); a raw-call utility makes no such assumption and can be called from anywhere, including tests that never touch the provider.

## Selective inheritance for preconditions

`extends AuthProviderRestfulBooker` only on tests that actually need the resulting `authSpec` (e.g. `PutUpdateBoking`). Tests that don't need auth (`PostCreateBookingTest`, `GetReadBookingData`) don't inherit it, even though they're testing the same resource. Inheriting a precondition "just in case" or "because the neighboring test needs it" ties unrelated tests to a dependency they don't use and couples their pass/fail to something outside what they're actually verifying.

## Test independence: atomic over flow

Chose atomic tests (each test creates its own fixture data and asserts one operation) over a Day-21-style single flow (create → get → put chained in one sequence). Reasoning: in a flow, one failing step cascades and obscures which operation actually broke; atomic tests fail for exactly one reason each, which matches "full API coverage" better than "does this one scenario work end to end." Flow-style tests remain the right tool when the object under test *is* the sequence itself (a user journey), not the correctness of each operation.

## Test Data Builder

Lombok `@Builder` already generates the builder — no need to hand-write one. The thin layer on top is a static factory returning the **unfinished builder** (not the built object), pre-filled with sensible defaults:

```java
public static BookingRequest.BookingRequestBuilder valid() {
    return BookingRequest.builder()
            .firstname("Test")
            .lastname("User")
            /* ... */ ;
}
```

Callers override only what matters to their specific case (`BookingTestData.valid().firstname("Updated").build()`) instead of copy-pasting the whole constructor per test class. Returning the builder rather than the built object is what makes overriding possible — returning `BookingRequest` directly would collapse this back into a rigid Object Mother (one static method per fixed variant).

## Request vs. Response DTOs — kept separate on purpose

`BookingRequest`/`BookingdatesRequest` and `BookingDetailsResponse`/`BookingdatesResponse`/`BookingResponse` are distinct classes, even though today their fields are identical. Trade-off, made deliberately rather than by accident:
- **Cost:** duplication right now — a field rename has to happen in two places.
- **Benefit:** the split survives the API evolving asymmetrically (server-added fields on the response, fields accepted on write but never echoed back) without a breaking refactor later. It also reflects that "what I send" and "what I receive" are different concepts even when they happen to look the same.
- **Safety net:** `usingRecursiveComparison()` matches by field name, not by class — if the two DTOs drift out of sync by accident, the comparison surfaces it as a failing field rather than silently ignoring it.

## Assertions

- **Cross-type recursive comparison** (AssertJ `usingRecursiveComparison()`) is the deliberate way to compare a request DTO against a differently-typed response DTO — it matches by field name, not by class, so comparing `BookingRequest` to `BookingDetailsResponse` is intentional, not a type-safety hole.
- **Server-generated fields that aren't on both sides** (e.g. `bookingid`, present only in the response wrapper): either compare against the nested object that genuinely excludes it (`response.jsonPath().getObject("booking", ...)`), or use `.ignoringFields("id")` when it's genuinely on the same level as the rest. Either way, the excluded field still gets its own explicit assertion — excluding it from one comparison isn't a reason to stop checking it.
- **Avoid vacuous assertions.** `assertNotNull(response)` right after `.extract().response()` can't meaningfully fail — RestAssured doesn't hand back `null` there. An assertion that can't fail under realistic conditions isn't testing anything; it's decoration. (Found twice now — `RestfulBookerAuthApi` and `GetReadBookingData`.)

## Naming discipline

Recurring typo pattern across this project (`Restfull`/`Restrull`/`RESFTFUL`, `Creat`, `Untility`, `Boking`) — not a one-off. Established habit going forward: read newly-introduced identifiers out loud before committing, as a deliberate final pass, since these don't reliably get caught by IDE highlighting or by reading for logic.