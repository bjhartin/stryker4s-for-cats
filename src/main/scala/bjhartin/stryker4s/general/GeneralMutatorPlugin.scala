package bjhartin.stryker4s.general

import stryker4s.plugin.CustomMutatorPlugin
import stryker4s.plugin.api.CustomMutator

/** Service provider registering the library's general-purpose, non-Cats mutators. */
class GeneralMutatorPlugin extends CustomMutatorPlugin {
  override def mutators: List[CustomMutator] = List(
    new ArithmeticOperatorMutator,
    new NumericLiteralMutator,
    new CollectionLiteralMutator,
    new CollectionOrderMutator,
    new StringNormalizationMutator,
    new HeadLastSwapMutator
  )
}
