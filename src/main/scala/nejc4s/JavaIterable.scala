package nejc4s

object JavaIterable {
  trait Base[A] extends JavaIterable[A] {
    //override def iterator: JavaIterator[A]

    //override def forEach(action: Consumer[_ >: A]): Unit = super.forEach(action)
    //override def spliterator: JavaSpliterator[A] = super.spliterator
  }
}
