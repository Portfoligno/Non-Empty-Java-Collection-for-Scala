package nejc4s

object JavaCollection {
  trait Base[A] extends JavaCollection[A] with JavaIterable.Base[A] {
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
}
