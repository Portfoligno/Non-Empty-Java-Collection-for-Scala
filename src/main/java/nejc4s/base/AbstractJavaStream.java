package nejc4s.base;

import java.util.function.IntFunction;
import java.util.stream.Stream;

interface AbstractJavaStream<A> extends Stream<A> {
  Object[] toArrayUntyped(IntFunction<Object[]> generator);

  @SuppressWarnings("unchecked")
  @Override
  default <T> T[] toArray(IntFunction<T[]> generator) {
    return (T[]) toArrayUntyped((IntFunction) generator);
  }
}
