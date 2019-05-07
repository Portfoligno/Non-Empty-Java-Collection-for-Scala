package nejc4s.base;

import java.util.Collection;

// Added to avoid 'illegal trait super target' error
public interface RefinedJavaCollectionBase<A> extends Collection<A> {
  @Override
  default Spliterator.Refined<A> spliterator() {
    return new Spliterator.UnsafeWrapper<>(Collection.super.spliterator());
  }
}
