package bjhartin.stryker4s.general

import bjhartin.stryker4s.SingleMutation
import stryker4s.plugin.api.*

import scala.meta.*

/** Custom mutator: swaps `+` for `-` (and vice versa) in `Int` arithmetic expressions.
  *
  * Covers the "Arithmetic Operator" mutator category that Stryker4s does not ship built-in.
  */
class ArithmeticOperatorMutator extends CustomMutator {
  def matcher: MutationMatcher = {
    case term @ Term.ApplyInfix.After_4_6_0(_, Term.Name("+"), Type.ArgClause(Nil), _) =>
      mutate(term, "-")
    case term @ Term.ApplyInfix.After_4_6_0(_, Term.Name("-"), Type.ArgClause(Nil), _) =>
      mutate(term, "+")
  }

  private def mutate(term: Term.ApplyInfix, to: String)(
      placeableTree: PlaceableTree
  ): Either[IgnoredMutations, Mutations] = {
    val from = term.op.value
    val mutated = term.copyWithComments(op = term.op.copyWithComments(value = to))
    val metadata = MutantMetadata(from, to, "ArithmeticOperator", term.pos, None)
    SingleMutation(placeableTree, term, mutated, metadata)
  }
}
