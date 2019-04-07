package nejc4s

import java.lang
import java.util.function._

import nejc4s.base.{JavaDoubleStream, PositiveLong}

trait NonEmptyJavaDoubleStream extends JavaDoubleStream.Refined {
  override def map(mapper: DoubleUnaryOperator): NonEmptyJavaDoubleStream
  override def mapToObj[B](mapper: DoubleFunction[_ <: B]): NonEmptyJavaStream[B]
  override def mapToInt(mapper: DoubleToIntFunction): NonEmptyJavaIntStream
  override def mapToLong(mapper: DoubleToLongFunction): NonEmptyJavaLongStream
  override def distinct: NonEmptyJavaDoubleStream
  override def sorted: NonEmptyJavaDoubleStream
  override def peek(action: DoubleConsumer): NonEmptyJavaDoubleStream
  //override def toArray(): Array[Double]
  //override def reduce(op: DoubleBinaryOperator): PresentDouble
  //override def min(): PresentDouble
  //override def max(): PresentDouble
  override def count(): PositiveLong
  //override def average(): PresentDouble
  //override def findFirst(): PresentDouble
  //override def findAny(): PresentDouble
  override def boxed: NonEmptyJavaStream[lang.Double]
  override def sequential(): NonEmptyJavaDoubleStream
  override def parallel(): NonEmptyJavaDoubleStream
  override def unordered(): NonEmptyJavaDoubleStream
}
