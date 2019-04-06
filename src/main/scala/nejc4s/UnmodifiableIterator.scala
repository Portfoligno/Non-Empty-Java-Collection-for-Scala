package nejc4s

private[nejc4s]
class UnmodifiableIterator[A](val delegate: JavaIterator[A]) extends JavaObject.UnsafeProxy with JavaIterator[A] {
  override def hasNext: Boolean = delegate.hasNext
  override def next: A = delegate.next()

  //override def remove(): Unit = super.remove()
  //override def forEachRemaining(action: Consumer[_ >: E]): Unit = super.forEachRemaining(action)
}
