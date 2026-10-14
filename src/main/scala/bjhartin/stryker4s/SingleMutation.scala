package bjhartin.stryker4s

import _root_.cats.data.NonEmptyVector
import _root_.cats.syntax.all.*
import stryker4s.plugin.api.*

import scala.meta.Term

object SingleMutation {

  /** One mutant replacing `original` within `placeableTree`, or an ignored mutant if it can't be placed. */
  def apply(
      placeableTree: PlaceableTree,
      original: Term,
      replacement: Term,
      metadata: MutantMetadata
  ): Either[IgnoredMutations, Mutations] =
    placeableTree
      .substitute(original, replacement)
      .bimap(
        reason => NonEmptyVector.one(MutatedCode(original, metadata) -> reason),
        code => NonEmptyVector.one(MutatedCode(code, metadata))
      )
}
