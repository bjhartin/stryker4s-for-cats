package bjhartin.stryker4s.general

import stryker4s.plugin.api.PlaceableTree

import scala.meta.*

class CollectionLiteralMutatorTest extends munit.FunSuite {
  private val mutator = new CollectionLiteralMutator

  private def mutate(code: String): String = {
    val term = code.parse[Term].get
    val Right(mutations) = mutator.matcher(term)(PlaceableTree(term)): @unchecked
    assertEquals(mutations.head.metadata.mutatorName, "CollectionLiteral")
    mutations.head.mutatedStatement.syntax
  }

  test("replaces non-empty List, Seq and Vector literals with empty") {
    assertEquals(mutate("List(1, 2)"), "List.empty")
    assertEquals(mutate("Seq(1)"), "Seq.empty")
    assertEquals(mutate("Vector(a)"), "Vector.empty")
  }

  test("does not match empty literals or other factories") {
    assert(!mutator.matcher.isDefinedAt("List()".parse[Term].get))
    assert(!mutator.matcher.isDefinedAt("Set(1)".parse[Term].get))
  }
}
