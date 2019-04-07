package nejc4s

import java.util.function.Consumer

object JavaIterator {
  private[nejc4s]
  type Refined[A] = JavaIterator[A] /*{
    override def hasNext: Boolean
    override def next(): A

    override def forEachRemaining(action: Consumer[_ >: A]): Unit = super.forEachRemaining(action)
  }*/

  trait Proxy[A] extends nejc4s.Proxy with Refined[A] {
    protected
    override def delegate: JavaIterator[A]

    override def hasNext: Boolean = delegate.hasNext
    override def next(): A = delegate.next()

    override def forEachRemaining(action: Consumer[_ >: A]): Unit = delegate.forEachRemaining(action)
  }

  class Unmodifiable[A](override val delegate: JavaIterator[A]) extends Proxy[A]
}
