package nejc4s.base

import java.util.function.Consumer

import nejc4s.base

object JavaIterable {
  trait Refined[A] extends JavaIterable[A] {
    //override def iterator: JavaIterator[A]

    //override def forEach(action: Consumer[_ >: A]): Unit = super.forEach(action)
    override def spliterator: Spliterator.Refined[A] = new Spliterator.UnsafeUnmodifiable(super.spliterator)
  }

  trait Proxy[A] extends base.Proxy with Refined[A] {
    protected
    override def delegate: JavaIterable[A]

    override def iterator: JavaIterator[A] = new JavaIterator.Unmodifiable(delegate.iterator)

    override def forEach(action: Consumer[_ >: A]): Unit = delegate.forEach(action)
    override def spliterator: Spliterator.Refined[A] = new Spliterator.UnsafeUnmodifiable(delegate.spliterator)
  }
}
