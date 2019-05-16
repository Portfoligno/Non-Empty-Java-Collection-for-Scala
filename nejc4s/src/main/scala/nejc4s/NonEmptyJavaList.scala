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
  ) extends UnsafeProxy[A] with JavaCollection[A]


  import syntax.seqView._

  def apply[A](x: A, xs: A*): NonEmptyJavaList[A] =
    new UnsafeWrapper((x +: xs.view.asSeq).asJava)

  def unapplySeq[A](xs: JavaList[A]): Option[(A, collection.Seq[A])] =
    xs match {
      case _: NonEmptyJavaList[A] =>
        Some(xs.iterator.next() -> xs.asScala.view.drop(1).asSeq)

      case _ =>
        try {
          Some(xs.get(0) -> xs.asScala.view.drop(1).asSeq)
        } catch {
          case _: IndexOutOfBoundsException =>
            None
        }
    }
}
