# stryker4s-for-cats

Cats and Cats Effect custom mutators for [Stryker4s](https://github.com/stryker-mutator/stryker4s).

The library is discovered automatically by Stryker4s through Java's `ServiceLoader`. Add the dependency to a project
that uses Stryker4s; no mutator class names or Stryker configuration are required.

This project currently targets the unreleased `stryker4s-plugin-api` from the custom plugin work. Change the
`stryker4s-plugin-api` version in `build.sbt` to the released version once that Stryker4s change is published.

## Included mutators

- Cats Effect conditional effects
- Error handling and fallback combinators
- Sequential/parallel sequencing
- Timeouts and sleeps
- `Option.getOrElse`
- `Resource` finalizers
- `Ref` updates

## Development

```bash
sbt test
```

The project is intentionally independent from the Stryker4s repository so it can be published and versioned
separately.
