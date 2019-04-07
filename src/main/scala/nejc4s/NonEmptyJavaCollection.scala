package nejc4s

import nejc4s.base.{False, JavaCollection, PositiveInt}

trait NonEmptyJavaCollection[A] extends JavaCollection.Refined[A] {
  override def size: PositiveInt
  override def isEmpty: False = False
  override def stream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(super.stream)
  override def parallelStream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(super.parallelStream)
}
