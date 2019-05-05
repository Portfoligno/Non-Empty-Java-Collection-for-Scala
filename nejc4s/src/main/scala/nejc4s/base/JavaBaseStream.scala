package nejc4s.base

object JavaBaseStream {
  trait Refined[A, S <: JavaBaseStream[A, S]] extends JavaBaseStream[A, S] with AutoCloseable.Refined {
    //override def iterator(): JavaIterator[A]
    override def spliterator(): Spliterator.Refined[A]
    //override def isParallel: Boolean
    //override def sequential(): S
    //override def parallel(): S
    //override def unordered(): S
    //override def onClose(closeHandler: Runnable): S
  }

  trait UnsafeProxy[A, S <: JavaBaseStream[A, S]] extends AutoCloseable.Proxy with Refined[A, S] {
    protected
    override def delegate: JavaBaseStream[A, S]

    override def iterator(): JavaIterator[A] = delegate.iterator()
    override def spliterator(): Spliterator.Refined[A] = new Spliterator.UnsafeWrapper(delegate.spliterator())
    override def isParallel: Boolean = delegate.isParallel
    override def sequential(): S = delegate.sequential()
    override def parallel(): S = delegate.parallel()
    override def unordered(): S = delegate.unordered()
    override def onClose(closeHandler: Runnable): S = delegate.onClose(closeHandler)
  }
}
