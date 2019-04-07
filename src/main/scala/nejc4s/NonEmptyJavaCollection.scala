package nejc4s

import nejc4s.base.{False, JavaCollection, PositiveInt}

trait NonEmptyJavaCollection[A] extends JavaCollection.Refined[A] {
  override def size: PositiveInt
  override def isEmpty: False = False
  //override def toArray: Array[AnyRef]
  //override def toArray[T](a: Array[T]): Array[T]
  override def stream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeUnmodifiable(super.stream)
  override def parallelStream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeUnmodifiable(super.parallelStream)
}
