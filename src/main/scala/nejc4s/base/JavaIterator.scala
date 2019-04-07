package nejc4s.base

import java.util.function.Consumer

import nejc4s.base

object JavaIterator {
  private[base]
  type Refined[A] = JavaIterator[A] /*{
    override def hasNext: Boolean
    override def next(): A

    override def forEachRemaining(action: Consumer[_ >: A]): Unit = super.forEachRemaining(action)
  }*/

  trait Proxy[A] extends base.Proxy with Refined[A] {
    protected
    override def delegate: JavaIterator[A]

    override def hasNext: Boolean = delegate.hasNext
    override def next(): A = delegate.next()

    override def forEachRemaining(action: Consumer[_ >: A]): Unit = delegate.forEachRemaining(action)
  }

  class Unmodifiable[A](override val delegate: JavaIterator[A]) extends Proxy[A]
}
