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

object NonEmptyJavaIntStream {
  trait UnsafeProxy extends JavaIntStream.UnsafeProxy with NonEmptyJavaIntStream {
    override def map(mapper: IntUnaryOperator): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.map(mapper))
    override def mapToObj[B](mapper: IntFunction[_ <: B]): NonEmptyJavaStream[B] =
      new NonEmptyJavaStream.UnsafeUnmodifiable(delegate.mapToObj(mapper))
    override def mapToLong(mapper: IntToLongFunction): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.mapToLong(mapper))
    override def mapToDouble(mapper: IntToDoubleFunction): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.mapToDouble(mapper))
    override def distinct: NonEmptyJavaIntStream = new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.distinct)
    override def sorted: NonEmptyJavaIntStream = new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.sorted)
    override def peek(action: IntConsumer): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.peek(action))
    //override def toArray(): Array[Int]
    override def reduce(op: IntBinaryOperator): PresentInt = PresentInt.unsafeFromOptionalInt(delegate.reduce(op))
    override def min(): PresentInt = PresentInt.unsafeFromOptionalInt(delegate.min())
    override def max(): PresentInt = PresentInt.unsafeFromOptionalInt(delegate.max())
    override def count(): PositiveLong = PositiveLong.unsafeFromLong(delegate.count())
    override def average(): PresentDouble = PresentDouble.unsafeFromOptionalDouble(delegate.average())
    override def findFirst(): PresentInt = PresentInt.unsafeFromOptionalInt(delegate.findFirst())
    override def findAny(): PresentInt = PresentInt.unsafeFromOptionalInt(delegate.findAny())
    override def asLongStream: NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeUnmodifiable(delegate.asLongStream())
    override def asDoubleStream: NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeUnmodifiable(delegate.asDoubleStream())
    override def boxed: NonEmptyJavaStream[Integer] = new NonEmptyJavaStream.UnsafeUnmodifiable(delegate.boxed)
    override def sequential(): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.sequential())
    override def parallel(): NonEmptyJavaIntStream = new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.parallel())
    override def unordered(): NonEmptyJavaIntStream = new NonEmptyJavaIntStream.UnsafeUnmodifiable(delegate.unordered())
  }

  class UnsafeUnmodifiable(override protected val delegate: JavaIntStream) extends UnsafeProxy
}
