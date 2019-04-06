package nejc4s

import java.util

object JavaCollection {
  trait Refined[A] extends JavaCollection[A] with JavaIterable.Refined[A] {
    override def size: NonNegativeInt
    //override def isEmpty: Boolean
    //override def contains(o: Any): Boolean
    //override def toArray: Array[AnyRef]
    //override def toArray[T](a: Array[T]): Array[T]
    //override def containsAll(c: JavaCollection[_]): Boolean

    //override def spliterator: JavaSpliterator[A] = super.spliterator
    //override def stream: JavaStream[A] = super.stream
    //override def parallelStream: JavaStream[A] = super.parallelStream
  }

  trait UnsafeProxy[A] extends util.AbstractCollection[A] with Refined[A] with JavaIterable.UnsafeProxy[A] {
    protected
    def delegate: JavaCollection[A]

    override def size: NonNegativeInt = NonNegativeInt.unsafeFromInt(delegate.size)
    override def isEmpty: Boolean = delegate.isEmpty
    override def contains(o: Any): Boolean = delegate.contains(o)
    override def toArray: Array[AnyRef] = delegate.toArray()
    //override def toArray[T](a: Array[T]): Array[T]
    override def containsAll(c: JavaCollection[_]): Boolean = delegate.containsAll(c)

    //override def spliterator: JavaSpliterator[A] = delegate.spliterator
    override def stream: JavaStream[A] = delegate.stream
    override def parallelStream: JavaStream[A] = delegate.parallelStream
  }
}
