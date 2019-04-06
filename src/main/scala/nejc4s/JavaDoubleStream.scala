package nejc4s

import java.util.function._
import java.util.stream.{DoubleStream, LongStream}
import java.util.{DoubleSummaryStatistics, PrimitiveIterator}

object JavaDoubleStream {
  trait Refined extends JavaDoubleStream with JavaBaseStream.Refined[java.lang.Double, JavaDoubleStream] {
    override def filter(predicate: DoublePredicate): JavaDoubleStream.Refined
    override def map(mapper: DoubleUnaryOperator): JavaDoubleStream.Refined
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): JavaStream.Refined[B]
    override def mapToInt(mapper: DoubleToIntFunction): JavaIntStream.Refined
    override def mapToLong(mapper: DoubleToLongFunction): LongStream
    override def flatMap(mapper: DoubleFunction[_ <: DoubleStream]): JavaDoubleStream.Refined
    override def distinct: JavaDoubleStream.Refined
    override def sorted: JavaDoubleStream.Refined
    override def peek(action: DoubleConsumer): JavaDoubleStream.Refined
    override def limit(maxSize: Long): JavaDoubleStream.Refined
    override def skip(n: Long): JavaDoubleStream.Refined
    //override def forEach(action: DoubleConsumer): Unit
    //override def forEachOrdered(action: DoubleConsumer): Unit
    //override def toArray: Array[Double]
    //override def reduce(identity: Double, op: DoubleBinaryOperator): Double
    //override def reduce(op: DoubleBinaryOperator): OptionalDouble
    //override def collect[B](supplier: Supplier[B], accumulator: ObjDoubleConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum: Double
    //override def min: OptionalDouble
    //override def max: OptionalDouble
    override def count: Long
    //override def average: OptionalDouble
    override def summaryStatistics: DoubleSummaryStatistics
    //override def anyMatch(predicate: DoublePredicate): Boolean
    //override def allMatch(predicate: DoublePredicate): Boolean
    //override def noneMatch(predicate: DoublePredicate): Boolean
    //override def findFirst: OptionalDouble
    //override def findAny: OptionalDouble
    override def boxed: JavaStream.Refined[java.lang.Double]
    override def sequential: JavaDoubleStream.Refined
    override def parallel: JavaDoubleStream.Refined
    //override def iterator: PrimitiveIterator.OfDouble
    //override def spliterator: Spliterator.OfDouble
  }

  trait Proxy extends Refined with JavaBaseStream.Proxy[java.lang.Double, JavaDoubleStream] {
    protected
    override def delegate: JavaDoubleStream

    override def sequential: JavaDoubleStream.Refined = ???
    override def parallel: JavaDoubleStream.Refined = ???
    override def iterator: PrimitiveIterator.OfDouble = ???
    override def spliterator: Spliterator.OfDouble = ???
  }
}
