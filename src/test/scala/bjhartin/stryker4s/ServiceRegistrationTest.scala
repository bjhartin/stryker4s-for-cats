package bjhartin.stryker4s

import stryker4s.plugin.CustomMutatorPlugin

import java.util.ServiceLoader
import scala.jdk.CollectionConverters.*

class ServiceRegistrationTest extends munit.FunSuite {
  test("registers the Cats Effect and general mutator plugins") {
    val plugins = ServiceLoader.load(classOf[CustomMutatorPlugin]).iterator().asScala.toList

    assertEquals(
      plugins.map(_.getClass.getName).toSet,
      Set("bjhartin.stryker4s.cats.CatsEffectMutatorPlugin", "bjhartin.stryker4s.general.GeneralMutatorPlugin")
    )
    assert(plugins.forall(_.mutators.nonEmpty))
  }
}
