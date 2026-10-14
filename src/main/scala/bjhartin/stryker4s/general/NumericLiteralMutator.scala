package bjhartin.stryker4s.general

import bjhartin.stryker4s.SingleMutation
import stryker4s.plugin.api.*

import scala.meta.*

/** Custom mutator: increments integer literals by 1 (e.g. `10` -> `11`).
  *
  * Covers the "Numeric Literal" mutator category that Stryker4s does not ship built-in. Purely syntactic (operates on `Lit.Int` nodes), with no dependency on type
  * information, like Stryker4s's built-in `StringLiteral`/`BooleanLiteral` mutators. Produces a single (+1) mutant
  * per literal.
  */
class NumericLiteralMutator extends CustomMutator {
  def matcher: MutationMatcher = { case lit @ Lit.Int(value) =>
    mutate(lit, value + 1)
  }

  private def mutate(lit: Lit.Int, incremented: Int)(
      placeableTree: PlaceableTree
  ): Either[IgnoredMutations, Mutations] = {
    val from = lit.value.toString
    val metadata = MutantMetadata(from, incremented.toString, "NumericLiteral", lit.pos, None)
    SingleMutation(placeableTree, lit, Lit.Int(incremented), metadata)
  }
}
