package bjhartin.stryker4s.cats

import stryker4s.pluginapi.{CustomMutator, CustomMutatorPlugin}

/** Service provider registering the Cats Effect mutators shipped by this library. */
class CatsEffectMutatorPlugin extends CustomMutatorPlugin {
  override def mutators: Seq[CustomMutator] = Seq(
    new ConditionalEffectMutator,
    new ErrorHandlingMutator,
    new FallbackRemovalMutator,
    new FallbackInversionMutator,
    new SequencingSwapMutator,
    new SequencingRemovalMutator,
    new TimeoutRemovalMutator,
    new GetOrElseMutator,
    new ParallelSequentialSwapMutator,
    new ResourceFinalizerMutator,
    new RefUpdateMutator,
    new SleepRemovalMutator
  )
}
