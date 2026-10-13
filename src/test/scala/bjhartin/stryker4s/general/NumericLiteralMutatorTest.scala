package bjhartin.stryker4s.general

import stryker4s.plugin.api.PlaceableTree

import scala.meta.*

class NumericLiteralMutatorTest extends munit.FunSuite {
  private val mutator = new NumericLiteralMutator

  test("increments an integer literal inside its statement") {
    val statement = "quantity > 10".parse[Term].get
    val literal = statement.collect { case l: Lit.Int => l }.head

    val Right(mutations) = mutator.matcher(literal)(PlaceableTree(statement)): @unchecked
    assertEquals(mutations.head.mutatedStatement.syntax, "quantity > 11")
    assertEquals(mutations.head.metadata.mutatorName, "NumericLiteral")
  }

  test("does not match non-integer literals") {
    assert(!mutator.matcher.isDefinedAt("\"10\"".parse[Term].get))
  }
}
