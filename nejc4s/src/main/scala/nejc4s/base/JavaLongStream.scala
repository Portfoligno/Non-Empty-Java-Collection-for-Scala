package nejc4s.base

import java.lang
import java.util.LongSummaryStatistics
import java.util.function._
import java.util.stream.LongStream

object JavaLongStream {
  trait Refined extends JavaLongStream with JavaBaseStream.Refined[lang.Long, JavaLongStream] {
    override def filter(predicate: LongPredicate): JavaLongStream.Refined
    override def map(mapper: LongUnaryOperator): JavaLongStream.Refined
    override def mapToObj[B](mapper: LongFunction[_ <: B]): JavaStream.Refined[B]
    override def mapToInt(mapper: LongToIntFunction): JavaIntStream.Refined
    override def mapToDouble(mapper: LongToDoubleFunction): JavaDoubleStream.Refined
    override def flatMap(mapper: LongFunction[_ <: LongStream]): JavaLongStream.Refined
    override def distinct: JavaLongStream.Refined
    override def sorted: JavaLongStream.Refined
    override def peek(action: LongConsumer): JavaLongStream.Refined
    override def limit(maxSize: Long): JavaLongStream.Refined
    override def skip(n: Long): JavaLongStream.Refined
    //override def forEach(action: LongConsumer): Unit
    //override def forEachOrdered(action: LongConsumer): Unit
    //override def toArray(): Array[Long]
    //override def reduce(identity: Long, op: LongBinaryOperator): Long
    //override def reduce(op: LongBinaryOperator): OptionalLong
    //override def collect[B](supplier: Supplier[B], accumulator: ObjLongConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum(): Long
    //override def min(): OptionalLong
    //override def max(): OptionalLong
    override def count(): NaturalLong
    //override def average(): OptionalDouble
    override def summaryStatistics(): LongSummaryStatistics
    //override def anyMatch(predicate: LongPredicate): Boolean
    //override def allMatch(predicate: LongPredicate): Boolean
    //override def noneMatch(predicate: LongPredicate): Boolean
    //override def findFirst(): OptionalLong
    //override def findAny(): OptionalLong
    override def asDoubleStream: JavaDoubleStream.Refined
    override def boxed: JavaStream.Refined[lang.Long]
    override def sequential(): JavaLongStream.Refined
    override def parallel(): JavaLongStream.Refined
    //override def iterator(): JavaPrimitiveIterator.OfLong
    override def spliterator(): Spliterator.OfLong.Refined

    override def unordered(): JavaLongStream.Refined
  }

  trait UnsafeProxy extends JavaBaseStream.UnsafeProxy[lang.Long, JavaLongStream] with Refined {
    protected
    override def delegate: JavaLongStream

    override def filter(predicate: LongPredicate): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.filter(predicate))
    override def map(mapper: LongUnaryOperator): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.map(mapper))
    override def mapToObj[B](mapper: LongFunction[_ <: B]): JavaStream.Refined[B] =
      new JavaStream.UnsafeWrapper(delegate.mapToObj(mapper))
    override def mapToInt(mapper: LongToIntFunction): JavaIntStream.Refined =
      new JavaIntStream.UnsafeWrapper(delegate.mapToInt(mapper))
    override def mapToDouble(mapper: LongToDoubleFunction): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.mapToDouble(mapper))
    override def flatMap(mapper: LongFunction[_ <: LongStream]): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.flatMap(mapper))
    override def distinct: JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.distinct)
    override def sorted: JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.sorted)
    override def peek(action: LongConsumer): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.peek(action))
    override def limit(maxSize: Long): JavaLongStream.Refined =
      new JavaLongStream.UnsafeWrapper(delegate.limit(maxSize))
    override def skip(n: Long): JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.skip(n))
    override def forEach(action: LongConsumer): Unit = delegate.forEach(action)
    override def forEachOrdered(action: LongConsumer): Unit = delegate.forEachOrdered(action)
    override def toArray(): Array[Long] = delegate.toArray()
    override def reduce(identity: Long, op: LongBinaryOperator): Long = delegate.reduce(identity, op)
    override def reduce(op: LongBinaryOperator): OptionalLong = delegate.reduce(op)
    override def collect[B](supplier: Supplier[B], accumulator: ObjLongConsumer[B], combiner: BiConsumer[B, B]): B =
      delegate.collect(supplier, accumulator, combiner)
    override def sum(): Long = delegate.sum()
    override def min(): OptionalLong = delegate.min()
    override def max(): OptionalLong = delegate.max()
    override def count(): NaturalLong = NaturalLong.unsafeFromLong(delegate.count())
    override def average(): OptionalDouble = delegate.average()
    override def summaryStatistics(): LongSummaryStatistics = delegate.summaryStatistics()
    override def anyMatch(predicate: LongPredicate): Boolean = delegate.anyMatch(predicate)
    override def allMatch(predicate: LongPredicate): Boolean = delegate.allMatch(predicate)
    override def noneMatch(predicate: LongPredicate): Boolean = delegate.noneMatch(predicate)
    override def findFirst(): OptionalLong = delegate.findFirst()
    override def findAny(): OptionalLong = delegate.findAny()
    override def asDoubleStream: JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeWrapper(delegate.asDoubleStream)
    override def boxed: JavaStream.Refined[lang.Long] = new JavaStream.UnsafeWrapper(delegate.boxed)
    override def sequential(): JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.sequential())
    override def parallel(): JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.parallel())
    override def iterator(): JavaPrimitiveIterator.OfLong = delegate.iterator()
    override def spliterator(): Spliterator.OfLong.Refined =
      new Spliterator.OfLong.UnsafeWrapper(delegate.spliterator())

    override def unordered(): JavaLongStream.Refined = new JavaLongStream.UnsafeWrapper(delegate.unordered())
  }

  class UnsafeWrapper(override protected val delegate: JavaLongStream) extends UnsafeProxy
}
