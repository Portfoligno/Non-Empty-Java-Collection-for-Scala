package nejc4s

object JavaListIterator {
  trait Refined[A] extends JavaListIterator[A] with JavaIterator.Refined[A] {
    //override def hasPrevious: Boolean
    //override def previous(): A
    override def nextIndex: NaturalInt
    override def previousIndex: NonoInt
  }

  trait UnsafeProxy[A] extends Refined[A] with JavaIterator.Proxy[A] {
    protected
    override def delegate: JavaListIterator[A]

    override def hasPrevious: Boolean = delegate.hasPrevious
    override def previous(): A = delegate.previous()
    override def nextIndex: NaturalInt = NaturalInt.unsafeFromInt(delegate.nextIndex)
    override def previousIndex: NonoInt = NonoInt.unsafeFromInt(delegate.previousIndex)

    override def set(e: A): Unit = throw new UnsupportedOperationException("set")
    override def add(e: A): Unit = throw new UnsupportedOperationException("add")
  }

  case class UnsafeUnmodifiable[A](override val delegate: JavaListIterator[A]) extends UnsafeProxy[A]
}
