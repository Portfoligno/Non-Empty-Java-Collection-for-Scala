package nejc4s

import java.util.Comparator
import java.util.function.{BinaryOperator, Consumer, Function}

import nejc4s.base.{JavaStream, PositiveLong, Present}

trait NonEmptyJavaStream[A] extends JavaStream.Refined[A] {
  override def map[B](mapper: Function[_ >: A, _ <: B]): NonEmptyJavaStream[B]
  //override def mapToInt(mapper: ToIntFunction[_ >: A]): JavaIntStream.Refined
  //override def mapToLong(mapper: ToLongFunction[_ >: A]): JavaLongStream.Refined
  //override def mapToDouble(mapper: ToDoubleFunction[_ >: A]): JavaDoubleStream.Refined
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
}
