package nejc4s

import java.util

object JavaCollection {
  trait Refined[A] extends JavaCollection[A] with JavaIterable.Refined[A] {
    override def size: NaturalInt
    //override def isEmpty: Boolean
    //override def contains(o: Any): Boolean
    //override def toArray: Array[AnyRef]
    //override def toArray[T](a: Array[T]): Array[T]
    //override def containsAll(c: JavaCollection[_]): Boolean

    //override def spliterator: JavaSpliterator[A] = super.spliterator
    override def stream: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(super.stream)
    override def parallelStream: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(super.parallelStream)
  }

  trait UnsafeProxy[A] extends util.AbstractCollection[A] with Refined[A] with JavaIterable.Proxy[A] {
    protected
    override def delegate: JavaCollection[A]

    override def size: NaturalInt = NaturalInt.unsafeFromInt(delegate.size)
    override def isEmpty: Boolean = delegate.isEmpty
    override def contains(o: Any): Boolean = delegate.contains(o)
    override def toArray: Array[AnyRef] = delegate.toArray()
    //override def toArray[T](a: Array[T]): Array[T]
    override def containsAll(c: JavaCollection[_]): Boolean = delegate.containsAll(c)

    //override def spliterator: JavaSpliterator[A] = delegate.spliterator
    override def stream: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(delegate.stream)
    override def parallelStream: JavaStream.Refined[A] = new JavaStream.UnsafeUnmodifiable(delegate.parallelStream)
  }
}
