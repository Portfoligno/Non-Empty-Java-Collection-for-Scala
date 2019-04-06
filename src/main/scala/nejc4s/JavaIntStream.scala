package nejc4s

import java.util.function.{IntConsumer, IntFunction, IntPredicate, IntToDoubleFunction, IntToLongFunction, IntUnaryOperator}
import java.util.stream.{IntStream, LongStream}
import java.util.{IntSummaryStatistics, PrimitiveIterator}

object JavaIntStream {
  trait Refined extends JavaIntStream with JavaBaseStream.Refined[Integer, JavaIntStream] {
    override def filter(predicate: IntPredicate): JavaIntStream.Refined
    override def map(mapper: IntUnaryOperator): JavaIntStream.Refined
    override def mapToObj[B](mapper: IntFunction[_ <: B]): JavaStream.Refined[B]
    override def mapToLong(mapper: IntToLongFunction): LongStream
    override def mapToDouble(mapper: IntToDoubleFunction): JavaDoubleStream.Refined
    override def flatMap(mapper: IntFunction[_ <: IntStream]): JavaIntStream.Refined
    override def distinct: JavaIntStream.Refined
    override def sorted: JavaIntStream.Refined
    override def peek(action: IntConsumer): JavaIntStream.Refined
    override def limit(maxSize: Long): JavaIntStream.Refined
    override def skip(n: Long): JavaIntStream.Refined
    //override def forEach(action: IntConsumer): Unit
    //override def forEachOrdered(action: IntConsumer): Unit
    //override def toArray: Array[Int]
    //override def reduce(identity: Int, op: IntBinaryOperator): Int
    //override def reduce(op: IntBinaryOperator): OptionalInt
    //override def collect[B](supplier: Supplier[B], accumulator: ObjIntConsumer[B], combiner: BiConsumer[B, B]): B
    //override def sum: Int
    //override def min: OptionalInt
    //override def max: OptionalInt
    override def count: Long
    //override def average: OptionalDouble
    override def summaryStatistics: IntSummaryStatistics
    //override def anyMatch(predicate: IntPredicate): Boolean
    //override def allMatch(predicate: IntPredicate): Boolean
    //override def noneMatch(predicate: IntPredicate): Boolean
    //override def findFirst: OptionalInt
    //override def findAny: OptionalInt
    override def asLongStream: LongStream
    override def asDoubleStream: JavaDoubleStream.Refined
    override def boxed: JavaStream.Refined[Integer]
    override def sequential: JavaIntStream.Refined
    override def parallel: JavaIntStream.Refined
    //override def iterator: PrimitiveIterator.OfInt
    //override def spliterator: Spliterator.OfInt
  }

  trait Proxy extends Refined with JavaBaseStream.Proxy[Integer, JavaIntStream] {
    protected
    override def delegate: JavaIntStream

    override def sequential: JavaIntStream.Refined = ???
    override def parallel: JavaIntStream.Refined = ???
    override def iterator: PrimitiveIterator.OfInt = ???
    override def spliterator: Spliterator.OfInt = ???
  }
}
