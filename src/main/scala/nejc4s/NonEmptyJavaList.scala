package nejc4s

import nejc4s.base.{JavaCollection, JavaList, PositiveIntX}

trait NonEmptyJavaList[A] extends JavaList.Refined[A] with NonEmptyJavaCollection[A] {
  override def indexOf(o: Any): PositiveIntX
  override def lastIndexOf(o: Any): PositiveIntX
}

object NonEmptyJavaList {
  trait UnsafeProxy[A]
    extends NonEmptyJavaCollection.UnsafeProxy[A]
      with JavaList.UnsafeProxy[A]
      with NonEmptyJavaList[A] {
    override def indexOf(o: Any): PositiveIntX = PositiveIntX.unsafeFromInt(delegate.indexOf(o))
    override def lastIndexOf(o: Any): PositiveIntX = PositiveIntX.unsafeFromInt(delegate.lastIndexOf(o))
  }

  class UnsafeWrapper[A](
    override protected val delegate: JavaList[A]
  ) extends UnsafeProxy[A] with JavaList[A] with JavaCollection[A]


  import scala.collection.JavaConverters._

  def apply[A](x: A, xs: A*): JavaList.Refined[A] =
    new UnsafeWrapper((x +: xs.view).asJava)

  def unapplySeq[A](xs: NonEmptyJavaList[A]): Some[(A, Seq[A])] = {
    val v = xs.asScala
    Some(v.head -> v.view.tail)
  }
}
