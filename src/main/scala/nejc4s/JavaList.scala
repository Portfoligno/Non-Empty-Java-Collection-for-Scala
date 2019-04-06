package nejc4s

object JavaList {
  trait Refined[A] extends JavaList[A] with JavaCollection.Refined[A] {
    //override def get(index: Int): A
    override def indexOf(o: Any): NonoInt
    override def lastIndexOf(o: Any): NonoInt
    override def listIterator: JavaListIterator.Refined[A]
    override def listIterator(index: Int): JavaListIterator.Refined[A]
    override def subList(fromIndex: Int, toIndex: Int): JavaList.Refined[A]

    //override def spliterator: JavaSpliterator[A] = super.spliterator
  }

  trait UnsafeProxy[A] extends JavaCollection.UnsafeProxy[A] with Refined[A] {
    protected
    override def delegate: JavaList[A]

    override def get(index: Int): A = delegate.get(index)
    override def indexOf(o: Any): NonoInt = NonoInt.unsafeFromInt(delegate.indexOf(o))
    override def lastIndexOf(o: Any): NonoInt = NonoInt.unsafeFromInt(delegate.lastIndexOf(o))
    override def listIterator: JavaListIterator.Refined[A] =
      new JavaListIterator.UnsafeUnmodifiable(delegate.listIterator)
    override def listIterator(index: Int): JavaListIterator.Refined[A] =
      new JavaListIterator.UnsafeUnmodifiable(delegate.listIterator(index))
    override def subList(fromIndex: Int, toIndex: Int): JavaList.Refined[A] =
      new UnsafeUnmodifiable(delegate.subList(fromIndex, toIndex))

    override def spliterator: Spliterator[A] = delegate.spliterator

    override def addAll(index: Int, c: JavaCollection[_ <: A]): Boolean =
      throw new UnsupportedOperationException("addAll")
    override def set(index: Int, element: A): A = throw new UnsupportedOperationException("set")
    override def add(index: Int, element: A): Unit = throw new UnsupportedOperationException("add")
    override def remove(index: Int): A = throw new UnsupportedOperationException("remove")
  }

  class UnsafeUnmodifiable[A](override protected val delegate: JavaList[A]) extends UnsafeProxy[A]


  import scala.collection.JavaConverters._

  def apply[A](xs: A*): JavaList.Refined[A] =
    new UnsafeUnmodifiable[A](xs.asJava)

  def unapplySeq[A](xs: JavaList[A]): Some[Seq[A]] =
    Some(xs.asScala)
}
