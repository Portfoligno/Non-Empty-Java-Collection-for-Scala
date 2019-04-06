package nejc4s

import java.lang
import java.util.function._
import java.util.stream.DoubleStream
import java.util.{DoubleSummaryStatistics, OptionalDouble, PrimitiveIterator}

object JavaDoubleStream {
  trait Refined extends JavaDoubleStream with JavaBaseStream.Refined[lang.Double, JavaDoubleStream] {
    override def filter(predicate: DoublePredicate): JavaDoubleStream.Refined
    override def map(mapper: DoubleUnaryOperator): JavaDoubleStream.Refined
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): JavaStream.Refined[B]
    override def mapToInt(mapper: DoubleToIntFunction): JavaIntStream.Refined
    override def mapToLong(mapper: DoubleToLongFunction): JavaLongStream.Refined
    override def flatMap(mapper: DoubleFunction[_ <: DoubleStream]): JavaDoubleStream.Refined
    override def distinct: JavaDoubleStream.Refined
    override def sorted: JavaDoubleStream.Refined
    override def peek(action: DoubleConsumer): JavaDoubleStream.Refined
    override def limit(maxSize: Long): JavaDoubleStream.Refined
    override def skip(n: Long): JavaDoubleStream.Refined
    //override def forEach(action: DoubleConsumer): Unit
    //override def forEachOrdered(action: DoubleConsumer): Unit
    //override def toArray(): Array[Double]
    //override def reduce(identity: Double, op: DoubleBinaryOperator): Double
    //override def reduce(op: DoubleBinaryOperator): OptionalDouble
    //override def collect[B](supplier: Supplier[B], accumulator: ObjDoubleConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum(): Double
    //override def min(): OptionalDouble
    //override def max(): OptionalDouble
    override def count(): NaturalLong
    //override def average(): OptionalDouble
    override def summaryStatistics(): DoubleSummaryStatistics
    //override def anyMatch(predicate: DoublePredicate): Boolean
    //override def allMatch(predicate: DoublePredicate): Boolean
    //override def noneMatch(predicate: DoublePredicate): Boolean
    //override def findFirst(): OptionalDouble
    //override def findAny(): OptionalDouble
    override def boxed: JavaStream.Refined[lang.Double]
    override def sequential(): JavaDoubleStream.Refined
    override def parallel(): JavaDoubleStream.Refined
    //override def iterator(): PrimitiveIterator.OfDouble
    //override def spliterator(): Spliterator.OfDouble
  }

  trait UnsafeProxy extends Refined with JavaBaseStream.Proxy[lang.Double, JavaDoubleStream] {
    protected
    override def delegate: JavaDoubleStream

    override def filter(predicate: DoublePredicate): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.filter(predicate))
    override def map(mapper: DoubleUnaryOperator): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.map(mapper))
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): JavaStream.Refined[B] =
      new JavaStream.UnsafeUnmodifiable(delegate.mapToObj(mapper))
    override def mapToInt(mapper: DoubleToIntFunction): JavaIntStream.Refined =
      new JavaIntStream.UnsafeUnmodifiable(delegate.mapToInt(mapper))
    override def mapToLong(mapper: DoubleToLongFunction): JavaLongStream.Refined =
      new JavaLongStream.UnsafeUnmodifiable(delegate.mapToLong(mapper))
    override def flatMap(mapper: DoubleFunction[_ <: DoubleStream]): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.flatMap(mapper))
    override def distinct: JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeUnmodifiable(delegate.distinct)
    override def sorted: JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeUnmodifiable(delegate.sorted)
    override def peek(action: DoubleConsumer): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.peek(action))
    override def limit(maxSize: Long): JavaDoubleStream.Refined =
      new JavaDoubleStream.UnsafeUnmodifiable(delegate.limit(maxSize))
    override def skip(n: Long): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeUnmodifiable(delegate.skip(n))
    override def forEach(action: DoubleConsumer): Unit = delegate.forEach(action)
    override def forEachOrdered(action: DoubleConsumer): Unit = delegate.forEachOrdered(action)
    override def toArray(): Array[Double] = delegate.toArray()
    override def reduce(identity: Double, op: DoubleBinaryOperator): Double = delegate.reduce(identity, op)
    override def reduce(op: DoubleBinaryOperator): OptionalDouble = delegate.reduce(op)
    override def collect[B](supplier: Supplier[B], accumulator: ObjDoubleConsumer[B], combiner: BiConsumer[B, B]): B =
      delegate.collect(supplier, accumulator, combiner)
    override def sum(): Double = delegate.sum()
    override def min(): OptionalDouble = delegate.min()
    override def max(): OptionalDouble = delegate.max()
    override def count(): NaturalLong = NaturalLong.unsafeFromLong(delegate.count())
    override def average(): OptionalDouble = delegate.average()
    override def summaryStatistics(): DoubleSummaryStatistics = delegate.summaryStatistics()
    override def anyMatch(predicate: DoublePredicate): Boolean = delegate.anyMatch(predicate)
    override def allMatch(predicate: DoublePredicate): Boolean = delegate.allMatch(predicate)
    override def noneMatch(predicate: DoublePredicate): Boolean = delegate.noneMatch(predicate)
    override def findFirst(): OptionalDouble = delegate.findFirst()
    override def findAny(): OptionalDouble = delegate.findAny()
    override def boxed: JavaStream.Refined[lang.Double] = new JavaStream.UnsafeUnmodifiable(delegate.boxed())
    override def sequential(): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeUnmodifiable(delegate.sequential())
    override def parallel(): JavaDoubleStream.Refined = new JavaDoubleStream.UnsafeUnmodifiable(delegate.parallel())
    override def iterator(): PrimitiveIterator.OfDouble = delegate.iterator()
    override def spliterator(): Spliterator.OfDouble = delegate.spliterator()
  }

  class UnsafeUnmodifiable(override val delegate: JavaDoubleStream) extends UnsafeProxy
}
