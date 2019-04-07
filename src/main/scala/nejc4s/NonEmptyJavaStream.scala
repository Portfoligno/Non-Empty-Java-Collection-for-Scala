package nejc4s

import java.util.Comparator
import java.util.function.{BinaryOperator, Consumer, Function, ToDoubleFunction, ToIntFunction, ToLongFunction}

import nejc4s.base.{JavaStream, PositiveLong, Present}

trait NonEmptyJavaStream[A] extends JavaStream.Refined[A] {
  override def map[B](mapper: Function[_ >: A, _ <: B]): NonEmptyJavaStream[B]
  override def mapToInt(mapper: ToIntFunction[_ >: A]): NonEmptyJavaIntStream
  override def mapToLong(mapper: ToLongFunction[_ >: A]): NonEmptyJavaLongStream
  override def mapToDouble(mapper: ToDoubleFunction[_ >: A]): NonEmptyJavaDoubleStream
  override def distinct: NonEmptyJavaStream[A]
  override def sorted: NonEmptyJavaStream[A]
  override def sorted(comparator: Comparator[_ >: A]): NonEmptyJavaStream[A]
  override def peek(action: Consumer[_ >: A]): NonEmptyJavaStream[A]
  //override def toArray(): Array[AnyRef]
  //override def toArray[T](generator: IntFunction[Array[T]]): Array[T]
  override def reduce(accumulator: BinaryOperator[A]): Present[A]
  override def min(comparator: Comparator[_ >: A]): Present[A]
  override def max(comparator: Comparator[_ >: A]): Present[A]
  override def count(): PositiveLong
  override def findFirst(): Present[A]
  override def findAny(): Present[A]
  override def sequential(): NonEmptyJavaStream[A]
  override def parallel(): NonEmptyJavaStream[A]
  override def unordered(): NonEmptyJavaStream[A]
}

object NonEmptyJavaStream {
  trait UnsafeProxy[A] extends JavaStream.UnsafeProxy[A] with NonEmptyJavaStream[A] {
    override def map[B](mapper: Function[_ >: A, _ <: B]): NonEmptyJavaStream[B] =
      new NonEmptyJavaStream.UnsafeWrapper(delegate.map(mapper))
    override def mapToInt(mapper: ToIntFunction[_ >: A]): NonEmptyJavaIntStream =
      new NonEmptyJavaIntStream.UnsafeWrapper(delegate.mapToInt(mapper))
    override def mapToLong(mapper: ToLongFunction[_ >: A]): NonEmptyJavaLongStream =
      new NonEmptyJavaLongStream.UnsafeWrapper(delegate.mapToLong(mapper))
    override def mapToDouble(mapper: ToDoubleFunction[_ >: A]): NonEmptyJavaDoubleStream =
      new NonEmptyJavaDoubleStream.UnsafeWrapper(delegate.mapToDouble(mapper))
    override def distinct: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.distinct)
    override def sorted: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.sorted)
    override def sorted(comparator: Comparator[_ >: A]): NonEmptyJavaStream[A] =
      new NonEmptyJavaStream.UnsafeWrapper(delegate.sorted(comparator))
    override def peek(action: Consumer[_ >: A]): NonEmptyJavaStream[A] =
      new NonEmptyJavaStream.UnsafeWrapper(delegate.peek(action))
    //override def toArray(): Array[AnyRef]
    //override def toArray[T](generator: IntFunction[Array[T]]): Array[T]
    override def reduce(accumulator: BinaryOperator[A]): Present[A] =
      Present.unsafeFromOptional(delegate.reduce(accumulator))
    override def min(comparator: Comparator[_ >: A]): Present[A] = Present.unsafeFromOptional(delegate.min(comparator))
    override def max(comparator: Comparator[_ >: A]): Present[A] = Present.unsafeFromOptional(delegate.max(comparator))
    override def count(): PositiveLong = PositiveLong.unsafeFromLong(delegate.count())
    override def findFirst(): Present[A] = Present.unsafeFromOptional(delegate.findFirst())
    override def findAny(): Present[A] = Present.unsafeFromOptional(delegate.findAny())
    override def sequential(): NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.sequential())
    override def parallel(): NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.parallel())
    override def unordered(): NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.unordered())
  }

  class UnsafeWrapper[A](override protected val delegate: JavaStream[A]) extends UnsafeProxy[A]
}
