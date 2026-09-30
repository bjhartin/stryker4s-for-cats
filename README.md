# stryker4s-for-cats

Cats / Cats Effect and general-purpose custom mutators for [Stryker4s](https://github.com/stryker-mutator/stryker4s).

The library is discovered automatically by Stryker4s through Java's `ServiceLoader`. Add the dependency to a project
that uses Stryker4s; no mutator class names or Stryker configuration are required.

This project currently targets the unreleased `stryker4s-plugin-api` from the custom plugin work. Change the
`stryker4s-plugin-api` version in `build.sbt` to the released version once that Stryker4s change is published.

## Included mutators

### Cats / Cats Effect mutators

- Cats Effect conditional effects
- Error handling and fallback combinators
- Sequential/parallel sequencing
- Timeouts and sleeps
- `Option.getOrElse`
- `Resource` finalizers
- `Ref` updates

### General-purpose mutators

- Arithmetic operators (`+` / `-`)
- Integer literals (`n` -> `n + 1`)
- Non-empty `List`/`Seq`/`Vector` literals -> `.empty`
- Dropping `sorted`/`sortBy`/`sortWith`/`reverse`/`distinct`/`distinctBy`
- Dropping string normalization (`trim`, `toUpperCase`, `stripPrefix`, ...)
- Swapping collection endpoints (`head`/`last`, `headOption`/`lastOption`, `tail`/`init`)

## Development

```bash
# stryker4s-plugin-api is not released yet; publish it from a Stryker4s checkout first
(cd ../stryker4s && sbt 'set ThisBuild / version := "0.0.0-DEV"; pluginApi/publishLocal')
sbt test
```

The project is intentionally independent from the Stryker4s repository so it can be published and versioned
separately.
