package bjhartin.stryker4s.cats

import cats.data.NonEmptyVector
import stryker4s.pluginapi.*

import scala.meta.*

/** Custom mutator: removes cats-effect fallback behavior by replacing `primary.orElse(fallback)` with just `primary`.
  */
class FallbackRemovalMutator extends CustomMutator {
  def matcher: MutationMatcher = {
    case term @ Term.Apply.After_4_6_0(
          Term.Select(primary, Term.Name("orElse")),
          Term.ArgClause(args, _)
        ) if args.size == 1 =>
      mutate(term, primary, ".orElse", "", "FallbackRemoval")
  }

  private def mutate(
      term: Term,
      replacement: Term,
      original: String,
      replacementDescription: String,
      mutatorName: String
  )(placeableTree: PlaceableTree): Either[IgnoredMutations, Mutations] = {
    val metadata = MutantMetadata(original, replacementDescription, mutatorName, term.pos, None)
    Right(NonEmptyVector.one(MutatedCode(placeableTree.substitute(term, replacement), metadata)))
  }
}

/** Custom mutator: inverts cats-effect fallback behavior by replacing `primary.orElse(fallback)` with just `fallback`.
  */
class FallbackInversionMutator extends CustomMutator {
  def matcher: MutationMatcher = {
    case term @ Term.Apply.After_4_6_0(
          Term.Select(_, Term.Name("orElse")),
          Term.ArgClause(args, _)
        ) if args.size == 1 =>
      val fallback = args.head
      mutate(term, fallback, ".orElse", "fallback", "FallbackInversion")
  }

  private def mutate(
      term: Term,
      replacement: Term,
      original: String,
      replacementDescription: String,
      mutatorName: String
  )(placeableTree: PlaceableTree): Either[IgnoredMutations, Mutations] = {
    val metadata = MutantMetadata(original, replacementDescription, mutatorName, term.pos, None)
    Right(NonEmptyVector.one(MutatedCode(placeableTree.substitute(term, replacement), metadata)))
  }
}
