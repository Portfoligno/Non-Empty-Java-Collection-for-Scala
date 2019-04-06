package nejc4s

import java.util.Comparator
import java.util.function._
import java.util.stream.{Collector, DoubleStream, IntStream, LongStream, Stream}

object JavaStream {
  trait Refined[A] extends JavaStream[A] with JavaBaseStream.Refined[A, JavaStream[A]] {
    override def filter(predicate: Predicate[_ >: A]): JavaStream.Refined[A]
    override def map[B](mapper: Function[_ >: A, _ <: B]): JavaStream.Refined[B]
    override def mapToInt(mapper: ToIntFunction[_ >: A]): JavaIntStream.Refined
    override def mapToLong(mapper: ToLongFunction[_ >: A]): JavaLongStream.Refined
    override def mapToDouble(mapper: ToDoubleFunction[_ >: A]): JavaDoubleStream.Refined
    override def flatMap[B](mapper: Function[_ >: A, _ <: Stream[_ <: B]]): JavaStream.Refined[B]
    override def flatMapToInt(mapper: Function[_ >: A, _ <: IntStream]): JavaIntStream.Refined
    override def flatMapToLong(mapper: Function[_ >: A, _ <: LongStream]): JavaLongStream.Refined
    override def flatMapToDouble(mapper: Function[_ >: A, _ <: DoubleStream]): JavaDoubleStream.Refined
    override def distinct: JavaStream.Refined[A]
    override def sorted: JavaStream.Refined[A]
    override def sorted(comparator: Comparator[_ >: A]): JavaStream.Refined[A]
    override def peek(action: Consumer[_ >: A]): JavaStream.Refined[A]
    override def limit(maxSize: Long): JavaStream.Refined[A]
    override def skip(n: Long): JavaStream.Refined[A]
    //override def forEach(action: Consumer[_ >: A]): Unit
    //override def forEachOrdered(action: Consumer[_ >: A]): Unit
    //override def toArray(): Array[AnyRef]
    //override def toArray[T](generator: IntFunction[Array[T]]): Array[T]
    //override def reduce(identity: A, accumulator: BinaryOperator[A]): A
    //override def reduce(accumulator: BinaryOperator[A]): Optional[A]
    //override def reduce[B](identity: B, accumulator: BiFunction[B, _ >: A, B], combiner: BinaryOperator[B]): B
    //override def collect[B](supplier: Supplier[B], accumulator: BiConsumer[B, _ >: A], combiner: BiConsumer[B, B]): B
    //override def collect[B, R](collector: Collector[_ >: A, R, B]): B
    //override def min(comparator: Comparator[_ >: A]): Optional[A]
    //override def max(comparator: Comparator[_ >: A]): Optional[A]
    override def count(): NaturalLong
    //override def anyMatch(predicate: Predicate[_ >: A]): Boolean
    //override def allMatch(predicate: Predicate[_ >: A]): Boolean
    //override def noneMatch(predicate: Predicate[_ >: A]): Boolean
    //override def findFirst(): Optional[A]
    //override def findAn()y: Optional[A]
  }

  trait UnsafeProxy[A] extends AbstractJavaStream[A] with Refined[A] with JavaBaseStream.Proxy[A, JavaStream[A]] {
    protected
    override def delegate: JavaStream[A]

    override def filter(predicate: Predicate[_ >: A]): JavaStream.Refined[A] =
      new JavaStream.UnsafeUnmodifiable(delegate.filter(predicate))
    override def map[B](mapper: Function[_ >: A, _ <: B]): JavaStream.Refined[B] =
      new JavaStream.UnsafeUnmodifiable(delegate.map(mapper))
    override def mapToInt(mapper: ToIntFunction[_ >: A]): JavaIntStream.Refined =
      new JavaIntStream.UnsafeUnmodifiable(delegate.mapToInt(mapper))
    override def mapToLong(mapper: ToLongFunction[_ >: A]): JavaLongStream.Refined =
      new JavaLongStream.UnsafeUnmodifiable(delegate.mapToLong(mapper))
    override def mapToDouble(mapper: ToDoubleFunction[_ >: A]): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.mapToDouble(mapper))
    override def flatMap[B](mapper: Function[_ >: A, _ <: Stream[_ <: B]]): JavaStream.Refined[B] =
      new JavaStream.UnsafeUnmodifiable(delegate.flatMap[B](mapper))
    override def flatMapToInt(mapper: Function[_ >: A, _ <: IntStream]): JavaIntStream.Refined =
      new JavaIntStream.UnsafeUnmodifiable(delegate.flatMapToInt(mapper))
    override def flatMapToLong(mapper: Function[_ >: A, _ <: LongStream]): JavaLongStream.Refined =
      new JavaLongStream.UnsafeUnmodifiable(delegate.flatMapToLong(mapper))
    override def flatMapToDouble(mapper: Function[_ >: A, _ <: DoubleStream]): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.flatMapToDouble(mapper))
    override def distinct: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(delegate.distinct)
    override def sorted: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(delegate.sorted)
    override def sorted(comparator: Comparator[_ >: A]): JavaStream.Refined[A] =
      new JavaStream.UnsafeUnmodifiable(delegate.sorted(comparator))
    override def peek(action: Consumer[_ >: A]): JavaStream.Refined[A] =
      new JavaStream.UnsafeUnmodifiable(delegate.peek(action))
    override def limit(maxSize: Long): JavaStream.Refined[A] =
      new JavaStream.UnsafeUnmodifiable(delegate.limit(maxSize))
    override def skip(n: Long): JavaStream.Refined[A] =
      new JavaStream.UnsafeUnmodifiable(delegate.skip(n))
    override def forEach(action: Consumer[_ >: A]): Unit = delegate.forEach(action)
    override def forEachOrdered(action: Consumer[_ >: A]): Unit = delegate.forEachOrdered(action)
    override def toArray(): Array[AnyRef] = delegate.toArray()
    override def toArrayUntyped(generator: IntFunction[Array[AnyRef]]): Array[AnyRef] = delegate.toArray(generator)
    override def reduce(identity: A, accumulator: BinaryOperator[A]): A = delegate.reduce(identity, accumulator)
    override def reduce(accumulator: BinaryOperator[A]): Optional[A] = delegate.reduce(accumulator)
    override def reduce[B](identity: B, accumulator: BiFunction[B, _ >: A, B], combiner: BinaryOperator[B]): B =
      delegate.reduce(identity, accumulator, combiner)
    override def collect[B](supplier: Supplier[B], accumulator: BiConsumer[B, _ >: A], combiner: BiConsumer[B, B]): B =
      delegate.collect(supplier, accumulator, combiner)
    override def collect[B, R](collector: Collector[_ >: A, R, B]): B = delegate.collect(collector)
    override def min(comparator: Comparator[_ >: A]): Optional[A] = delegate.min(comparator)
    override def max(comparator: Comparator[_ >: A]): Optional[A] = delegate.max(comparator)
    override def count(): NaturalLong = NaturalLong.unsafeFromLong(delegate.count())
    override def anyMatch(predicate: Predicate[_ >: A]): Boolean = delegate.anyMatch(predicate)
    override def allMatch(predicate: Predicate[_ >: A]): Boolean = delegate.allMatch(predicate)
    override def noneMatch(predicate: Predicate[_ >: A]): Boolean = delegate.noneMatch(predicate)
    override def findFirst(): Optional[A] = delegate.findFirst()
    override def findAny(): Optional[A] = delegate.findAny()
  }

  class UnsafeUnmodifiable[A](override val delegate: JavaStream[A]) extends UnsafeProxy[A]
}
