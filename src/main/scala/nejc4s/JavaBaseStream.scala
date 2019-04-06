package nejc4s

object JavaBaseStream {
  private[nejc4s]
  type Refined[A, S <: JavaBaseStream[A, S]] = JavaBaseStream[A, S] /*with AutoCloseable.Refined {
    override def iterator(): JavaIterator[A]
    override def spliterator(): Spliterator[A]
    override def isParallel: Boolean
    override def sequential(): S
    override def parallel(): S
    override def unordered(): S
    override def onClose(closeHandler: Runnable): S
  }*/

  trait Proxy[A, S <: Refined[A, S]] extends Refined[A, S] with AutoCloseable.Proxy {
    protected
    override def delegate: JavaBaseStream[A, S]

    override def iterator(): JavaIterator[A] = delegate.iterator()
    override def spliterator(): Spliterator[A] = delegate.spliterator()
    override def isParallel: Boolean = delegate.isParallel
    override def sequential(): S = delegate.sequential()
    override def parallel(): S = delegate.parallel()
    override def unordered(): S = delegate.unordered()
    override def onClose(closeHandler: Runnable): S = delegate.onClose(closeHandler)
  }
}
