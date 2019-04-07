package nejc4s

import java.lang
import java.util.function._

import nejc4s.base.{JavaLongStream, PositiveLong}

trait NonEmptyJavaLongStream extends JavaLongStream.Refined {
  override def map(mapper: LongUnaryOperator): NonEmptyJavaLongStream
  override def mapToObj[B](mapper: LongFunction[_ <: B]): NonEmptyJavaStream[B]
  override def mapToInt(mapper: LongToIntFunction): NonEmptyJavaIntStream
  override def mapToDouble(mapper: LongToDoubleFunction): NonEmptyJavaDoubleStream
  override def distinct: NonEmptyJavaLongStream
  override def sorted: NonEmptyJavaLongStream
  override def peek(action: LongConsumer): NonEmptyJavaLongStream
  //override def toArray(): Array[Long]
  //override def reduce(op: LongBinaryOperator): PresentLong
  //override def min(): PresentLong
  //override def max(): PresentLong
  override def count(): PositiveLong
  //override def average(): PresentDouble
  //override def findFirst(): PresentLong
  //override def findAny(): PresentLong
  override def asDoubleStream: NonEmptyJavaDoubleStream
  override def boxed: NonEmptyJavaStream[lang.Long]
  override def sequential(): NonEmptyJavaLongStream
  override def parallel(): NonEmptyJavaLongStream
}
