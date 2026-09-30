package bjhartin.stryker4s.general

import stryker4s.pluginapi.PlaceableTree

import scala.meta.*

class ArithmeticOperatorMutatorTest extends munit.FunSuite {
  private val mutator = new ArithmeticOperatorMutator

  private def mutate(code: String): String = {
    val term = code.parse[Term].get
    val Right(mutations) = mutator.matcher(term)(PlaceableTree(term)): @unchecked
    assertEquals(mutations.length, 1)
    assertEquals(mutations.head.metadata.mutatorName, "ArithmeticOperator")
    mutations.head.mutatedStatement.syntax
  }

  test("swaps + for -") {
    assertEquals(mutate("a + b"), "a - b")
  }

  test("swaps - for +") {
    assertEquals(mutate("a - b"), "a + b")
  }

  test("does not match other operators") {
    assert(!mutator.matcher.isDefinedAt("a * b".parse[Term].get))
  }
}
