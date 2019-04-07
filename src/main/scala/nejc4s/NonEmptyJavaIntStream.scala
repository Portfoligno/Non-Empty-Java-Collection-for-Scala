package nejc4s

import java.util.function._

import nejc4s.base.{JavaIntStream, PositiveLong, PresentDouble, PresentInt}

trait NonEmptyJavaIntStream extends JavaIntStream.Refined {
  override def map(mapper: IntUnaryOperator): NonEmptyJavaIntStream
  override def mapToObj[B](mapper: IntFunction[_ <: B]): NonEmptyJavaStream[B]
  override def mapToLong(mapper: IntToLongFunction): NonEmptyJavaLongStream
  override def mapToDouble(mapper: IntToDoubleFunction): NonEmptyJavaDoubleStream
  override def distinct: NonEmptyJavaIntStream
  override def sorted: NonEmptyJavaIntStream
  override def peek(action: IntConsumer): NonEmptyJavaIntStream
  //override def toArray(): Array[Int]
  override def reduce(op: IntBinaryOperator): PresentInt
  override def min(): PresentInt
  override def max(): PresentInt
  override def count(): PositiveLong
  override def average(): PresentDouble
  override def findFirst(): PresentInt
  override def findAny(): PresentInt
  override def asLongStream: NonEmptyJavaLongStream
  override def asDoubleStream: NonEmptyJavaDoubleStream
  override def boxed: NonEmptyJavaStream[Integer]
  override def sequential(): NonEmptyJavaIntStream
  override def parallel(): NonEmptyJavaIntStream
  override def unordered(): NonEmptyJavaIntStream
}
