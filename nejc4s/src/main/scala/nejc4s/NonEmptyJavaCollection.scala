package nejc4s

import nejc4s.base.{False, JavaCollection, PositiveInt}

trait NonEmptyJavaCollection[A] extends JavaCollection.Refined[A] with JavaCollection[A] {
  override def size: PositiveInt
  override def isEmpty: False = False
  override def stream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(super[Collection].stream)
  override def parallelStream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(super[Collection].parallelStream)
}

object NonEmptyJavaCollection {
  trait UnsafeProxy[A] extends JavaCollection.UnsafeProxy[A] with NonEmptyJavaCollection[A] {
    override def size: PositiveInt = PositiveInt.unsafeFromInt(delegate.size)
    override def isEmpty: False = { require(!delegate.isEmpty); False }
    override def stream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.stream)
    override def parallelStream: NonEmptyJavaStream[A] = new NonEmptyJavaStream.UnsafeWrapper(delegate.parallelStream)
  }
}
