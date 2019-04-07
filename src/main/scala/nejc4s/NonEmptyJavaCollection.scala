package nejc4s

import nejc4s.base.{False, JavaCollection, PositiveInt}

trait NonEmptyJavaCollection[A] extends JavaCollection.Refined[A] {
  override def size: PositiveInt
  override def isEmpty: False = False
  //override def toArray: Array[AnyRef]
  //override def toArray[T](a: Array[T]): Array[T]
  //override def stream: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(super.stream)
  //override def parallelStream: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(super.parallelStream)
}
