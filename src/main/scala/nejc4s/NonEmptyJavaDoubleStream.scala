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
      new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.map(mapper))
    override def mapToObj[B](mapper: DoubleFunction[_ <: B]): NonEmptyJavaStream[B] =
      new NonEmptyJavaStream.UnsafeWrapper(delegate.mapToObj(mapper))
    override def mapToInt(mapper: DoubleToIntFunction): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeWrapper(delegate.mapToInt(mapper))
    override def mapToLong(mapper: DoubleToLongFunction): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeWrapper(delegate.mapToLong(mapper))
    override def distinct: NonEmptyJavaDoubleStream = new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.distinct)
    override def sorted: NonEmptyJavaDoubleStream = new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.sorted)
    override def peek(action: DoubleConsumer): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.peek(action))
    override def reduce(op: DoubleBinaryOperator): PresentDouble =
      PresentDouble.unsafeFromOptionalDouble(delegate.reduce(op))
    override def min(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.min())
    override def max(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.max())
    override def count(): PositiveLong = PositiveLong.unsafeFromLong(delegate.count())
    override def average(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.average())
    override def findFirst(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.findFirst())
    override def findAny(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.findAny())
    override def boxed: NonEmptyJavaStream[lang.Double] = new NonEmptyJavaStream.UnsafeWrapper(delegate.boxed)
    override def sequential(): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.sequential())
    override def parallel(): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.parallel())
    override def unordered(): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.unordered())
  }

  class UnsafeWrapper(override protected val delegate: JavaDoubleStream) extends UnsafeProxy
}
