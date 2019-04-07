package nejc4s

import java.lang
import java.util.function._

import nejc4s.base.{JavaDoubleStream, PositiveLong, PresentDouble}

trait NonEmptyJavaDoubleStream extends JavaDoubleStream.Refined {
  override def map(mapper: DoubleUnaryOperator): NonEmptyJavaDoubleStream
  override def mapToObj[B](mapper: DoubleFunction[_ <: B]): NonEmptyJavaStream[B]
  override def mapToInt(mapper: DoubleToIntFunction): NonEmptyJavaIntStream
  override def mapToLong(mapper: DoubleToLongFunction): NonEmptyJavaLongStream
  override def distinct: NonEmptyJavaDoubleStream
  override def sorted: NonEmptyJavaDoubleStream
  override def peek(action: DoubleConsumer): NonEmptyJavaDoubleStream
  //override def toArray(): Array[Double]
  override def reduce(op: DoubleBinaryOperator): PresentDouble
  override def min(): PresentDouble
  override def max(): PresentDouble
  override def count(): PositiveLong
  override def average(): PresentDouble
  override def findFirst(): PresentDouble
  override def findAny(): PresentDouble
  override def boxed: NonEmptyJavaStream[lang.Double]
  override def sequential(): NonEmptyJavaDoubleStream
  override def parallel(): NonEmptyJavaDoubleStream
  override def unordered(): NonEmptyJavaDoubleStream
}

object NonEmptyJavaDoubleStream {
  trait UnsafeProxy extends JavaDoubleStream.UnsafeProxy with NonEmptyJavaDoubleStream {
    override def map(mapper: DoubleUnaryOperator): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.map(mapper))
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): NonEmptyJavaStream[B] =
      new NonEmptyJavaStream.UnsafeUnmodifiable(delegate.mapToObj(mapper))
    override def mapToInt(mapper: DoubleToIntFunction): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.mapToInt(mapper))
    override def mapToLong(mapper: DoubleToLongFunction): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.mapToLong(mapper))
    override def distinct: NonEmptyJavaDoubleStream = new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.distinct)
    override def sorted: NonEmptyJavaDoubleStream = new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.sorted)
    override def peek(action: DoubleConsumer): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.peek(action))
    //override def toArray(): Array[Double]
    override def reduce(op: DoubleBinaryOperator): PresentDouble =
      PresentDouble.unsafeFromOptionalDouble(delegate.reduce(op))
    override def min(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.min())
    override def max(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.max())
    override def count(): PositiveLong = PositiveLong.unsafeFromLong(delegate.count())
    override def average(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.average())
    override def findFirst(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.findFirst())
    override def findAny(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.findAny())
    override def boxed: NonEmptyJavaStream[lang.Double] = new NonEmptyJavaStream.UnsafeUnmodifiable(delegate.boxed)
    override def sequential(): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.sequential())
    override def parallel(): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.parallel())
    override def unordered(): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.unordered())
  }

  class UnsafeUnmodifiable(override protected val delegate: JavaDoubleStream) extends UnsafeProxy
}
