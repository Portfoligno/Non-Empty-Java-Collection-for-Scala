package nejc4s

import java.util.Comparator
import java.util.function._
import java.util.stream.{DoubleStream, IntStream, LongStream, Stream}

object JavaStream {
  trait Refined[A] extends JavaStream[A] with JavaBaseStream.Refined[A, JavaStream[A]] {
    override def filter(predicate: Predicate[_ >: A]): JavaStream.Refined[A]
    override def map[B](mapper: Function[_ >: A, _ <: B]): JavaStream.Refined[B]
    override def mapToInt(mapper: ToIntFunction[_ >: A]): IntStream
    override def mapToLong(mapper: ToLongFunction[_ >: A]): LongStream
    override def mapToDouble(mapper: ToDoubleFunction[_ >: A]): DoubleStream
    override def flatMap[B](mapper: Function[_ >: A, _ <: Stream[_ <: B]]): JavaStream.Refined[B]
    override def flatMapToInt(mapper: Function[_ >: A, _ <: IntStream]): IntStream
    override def flatMapToLong(mapper: Function[_ >: A, _ <: LongStream]): LongStream
    override def flatMapToDouble(mapper: Function[_ >: A, _ <: DoubleStream]): DoubleStream
    override def distinct: JavaStream.Refined[A]
    override def sorted: JavaStream.Refined[A]
    override def sorted(comparator: Comparator[_ >: A]): JavaStream.Refined[A]
    override def peek(action: Consumer[_ >: A]): JavaStream.Refined[A]
    override def limit(maxSize: Long): JavaStream.Refined[A]
    override def skip(n: Long): JavaStream.Refined[A]
    //override def forEach(action: Consumer[_ >: A]): Unit
    //override def forEachOrdered(action: Consumer[_ >: A]): Unit
    //override def toArray: Array[AnyRef]
    //override def toArray[T](generator: IntFunction[Array[T]]): Array[T]
    //override def reduce(identity: A, accumulator: BinaryOperator[A]): A
    //override def reduce(accumulator: BinaryOperator[A]): Optional[A]
    //override def reduce[B](identity: B, accumulator: BiFunction[B, _ >: A, B], combiner: BinaryOperator[B]): B
    //override def collect[B](supplier: Supplier[B], accumulator: BiConsumer[B, _ >: A], combiner: BiConsumer[B, B]): B
    //override def collect[B, R](collector: Collector[_ >: A, R, B]): B
    //override def min(comparator: Comparator[_ >: A]): Optional[A]
    //override def max(comparator: Comparator[_ >: A]): Optional[A]
    override def count: Long
    //override def anyMatch(predicate: Predicate[_ >: A]): Boolean
    //override def allMatch(predicate: Predicate[_ >: A]): Boolean
    //override def noneMatch(predicate: Predicate[_ >: A]): Boolean
    //override def findFirst: Optional[A]
    //override def findAny: Optional[A]
  }

  trait Proxy[A] extends Refined[A] with JavaBaseStream.Proxy[A, JavaStream[A]] {
    protected
    override def delegate: JavaStream[A]
  }
}
