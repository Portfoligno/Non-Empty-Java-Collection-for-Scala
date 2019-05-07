package nejc4s.base

import java.util

object JavaCollection {
  trait Refined[A] extends RefinedJavaCollectionBase[A] with JavaIterable.Refined[A] {
    override def size: NaturalInt
    //override def isEmpty: Boolean
    //override def contains(o: Any): Boolean
    //override def toArray: Array[AnyRef]
    //override def toArray[T](a: Array[T]): Array[T]
    //override def containsAll(c: JavaCollection[_]): Boolean

    //override def spliterator: Spliterator.Refined[A] = new Spliterator.UnsafeWrapper(super.spliterator)
    override def stream: JavaStream.Refined[A] = new JavaStream.UnsafeWrapper(super.stream)
    override def parallelStream: JavaStream.Refined[A] = new JavaStream.UnsafeWrapper(super.parallelStream)
  }

  trait UnsafeProxy[A] extends util.AbstractCollection[A] with JavaIterable.Proxy[A] with Refined[A] {
    protected
    override def delegate: JavaCollection[A]

    override def size: NaturalInt = NaturalInt.unsafeFromInt(delegate.size)
    override def isEmpty: Boolean = delegate.isEmpty
    override def contains(o: Any): Boolean = delegate.contains(o)
    override def toArray: Array[AnyRef] = delegate.toArray()
    //override def toArray[T](a: Array[T]): Array[T]
    override def containsAll(c: JavaCollection[_]): Boolean = delegate.containsAll(c)

    override def spliterator: Spliterator.Refined[A] = new Spliterator.UnsafeWrapper(delegate.spliterator)
    override def stream: JavaStream.Refined[A] = new JavaStream.UnsafeWrapper(delegate.stream)
    override def parallelStream: JavaStream.Refined[A] = new JavaStream.UnsafeWrapper(delegate.parallelStream)
  }
}
