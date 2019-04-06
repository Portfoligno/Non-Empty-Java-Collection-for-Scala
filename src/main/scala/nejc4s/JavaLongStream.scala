package nejc4s

import java.util.function._
import java.util.stream.LongStream
import java.util.{LongSummaryStatistics, PrimitiveIterator}

object JavaLongStream {
  trait Refined extends JavaLongStream with JavaBaseStream.Refined[java.lang.Long, JavaLongStream] {
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
    //override def toArray: Array[Long]
    //override def reduce(identity: Long, op: LongBinaryOperator): Long
    //override def reduce(op: LongBinaryOperator): OptionalLong
    //override def collect[B](supplier: Supplier[B], accumulator: ObjLongConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum: Long
    //override def min: OptionalLong
    //override def max: OptionalLong
    override def count: Long
    //override def average: OptionalDouble
    override def summaryStatistics: LongSummaryStatistics
    //override def anyMatch(predicate: LongPredicate): Boolean
    //override def allMatch(predicate: LongPredicate): Boolean
    //override def noneMatch(predicate: LongPredicate): Boolean
    //override def findFirst: OptionalLong
    //override def findAny: OptionalLong
    override def asDoubleStream: JavaDoubleStream.Refined
    override def boxed: JavaStream.Refined[java.lang.Long]
    override def sequential: JavaLongStream.Refined
    override def parallel: JavaLongStream.Refined
    //override def iterator: PrimitiveIterator.OfLong
    //override def spliterator: Spliterator.OfLong
  }

  trait Proxy extends Refined with JavaBaseStream.Proxy[java.lang.Long, JavaLongStream] {
    protected
    override def delegate: JavaLongStream

    override def sequential: JavaLongStream.Refined = ???
    override def parallel: JavaLongStream.Refined = ???
    override def iterator: PrimitiveIterator.OfLong = ???
    override def spliterator: Spliterator.OfLong = ???
  }
}
