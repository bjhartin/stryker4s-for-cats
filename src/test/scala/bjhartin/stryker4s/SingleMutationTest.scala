package bjhartin.stryker4s

import stryker4s.plugin.api.*

import scala.meta.*

class SingleMutationTest extends munit.FunSuite {
  private val metadata = MutantMetadata("a", "b", "Test", Position.None, None)

  test("returns one mutant with the original replaced in the statement") {
    val statement = "f(a)".parse[Term].get
    val original = statement.collect { case t @ Term.Name("a") => t }.head

    val Right(mutations) = SingleMutation(PlaceableTree(statement), original, Term.Name("b"), metadata): @unchecked

    assertEquals(mutations.length, 1)
    assertEquals(mutations.head.mutatedStatement.syntax, "f(b)")
  }

  test("returns an ignored mutant when the original is not in the statement") {
    val statement = "f(a)".parse[Term].get

    val Left(ignored) = SingleMutation(PlaceableTree(statement), Term.Name("z"), Term.Name("b"), metadata): @unchecked

    assertEquals(ignored.length, 1)
    assert(ignored.head._2.isInstanceOf[SubstitutionFailed], ignored.head._2)
  }
}
