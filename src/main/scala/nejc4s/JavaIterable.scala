package nejc4s

import java.util.function.Consumer

object JavaIterable {
  private[nejc4s]
  type Refined[A] = JavaIterable[A] /*{
    override def iterator: JavaIterator[A]

    override def forEach(action: Consumer[_ >: A]): Unit = super.forEach(action)
    override def spliterator: JavaSpliterator[A] = super.spliterator
  }*/

  trait Proxy[A] extends Refined[A] with nejc4s.Proxy {
    protected
    override def delegate: JavaIterable[A]

    override def iterator: JavaIterator[A] = JavaIterator.Unmodifiable(delegate.iterator)

    override def forEach(action: Consumer[_ >: A]): Unit = delegate.forEach(action)
    override def spliterator: Spliterator[A] = delegate.spliterator
  }
}
