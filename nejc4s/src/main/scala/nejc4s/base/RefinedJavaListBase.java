package nejc4s.base;

import java.util.List;

// Added to avoid 'illegal trait super target' error
public interface RefinedJavaListBase<A> extends List<A> {
  @Override
  default Spliterator.Refined<A> spliterator() {
    return new Spliterator.UnsafeWrapper<>(List.super.spliterator());
  }
}
