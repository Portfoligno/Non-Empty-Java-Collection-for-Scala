package nejc4s.syntax

import scala.collection.convert.{AsJavaExtensions, AsScalaExtensions}
import scala.collection.mutable.ArrayBuffer
import scala.collection.{AbstractSeq, IterableFactoryDefaults, Iterator, SeqFactory, SeqOps, SeqView, mutable}

private[nejc4s]
trait SeqViewSyntax extends AsJavaExtensions with AsScalaExtensions {
  implicit def toSeqViewOps[A](seqView: SeqView[A]): SeqViewOps[A] =
    new SeqViewOps(seqView)
}

private[nejc4s]
class SeqViewOps[A](private val seqView: SeqView[A]) extends AnyVal {
  def asSeq: collection.Seq[A] =
    SeqViewAsSeq(seqView)
}

private
case class SeqViewAsSeq[+A](underlying: SeqView[A]) extends AbstractSeq[A]
  with SeqOps[A, collection.Seq, collection.Seq[A]]
  with IterableFactoryDefaults[A, collection.Seq] {
  // Same as `JListWrapper`, but for `SeqView`
  def length: Int = underlying.size
  override def knownSize: Int = underlying.knownSize
  override def isEmpty: Boolean = underlying.isEmpty
  override def iterator: Iterator[A] = underlying.iterator
  def apply(i: Int): A = underlying(i)
  override def iterableFactory: SeqViewAsSeq.type = SeqViewAsSeq

  // Implement `SeqView` specific methods
  override def view: SeqView[A] = underlying
  override def map[B](f: A => B): SeqViewAsSeq[B] = SeqViewAsSeq(underlying.map(f))
  override def appended[B >: A](elem: B): SeqViewAsSeq[B] = SeqViewAsSeq(underlying.appended(elem))
  override def prepended[B >: A](elem: B): SeqViewAsSeq[B] = SeqViewAsSeq(underlying.prepended(elem))
  override def reverse: SeqViewAsSeq[A] = SeqViewAsSeq(underlying.reverse)
  override def take(n: Int): SeqViewAsSeq[A] = SeqViewAsSeq(underlying.take(n))
  override def drop(n: Int): SeqViewAsSeq[A] = SeqViewAsSeq(underlying.drop(n))
  override def takeRight(n: Int): SeqViewAsSeq[A] = SeqViewAsSeq(underlying.takeRight(n))
  override def dropRight(n: Int): SeqViewAsSeq[A] = SeqViewAsSeq(underlying.dropRight(n))
  override def sorted[B >: A](implicit ord: Ordering[B]): SeqViewAsSeq[A] = SeqViewAsSeq(underlying.sorted[B])

  // Implement `View` specific methods
  override def empty: SeqViewAsSeq[A] = SeqViewAsSeq.empty
  override def toString: String  = className + "(?)"
  override protected def className: String = "SeqViewAsSeq"
}

private
object SeqViewAsSeq extends SeqFactory[SeqViewAsSeq] {
  val _empty = SeqViewAsSeq(Nil.view)

  override
  def from[A](it: IterableOnce[A]): SeqViewAsSeq[A] = it match {
    case it: SeqViewAsSeq[A] => it
    case it: SeqView[A] => SeqViewAsSeq(it)
    case _ => SeqViewAsSeq(LazyList.from(it).view)
  }

  override
  def empty[A]: SeqViewAsSeq[A] =
    _empty

  override
  def newBuilder[A]: mutable.Builder[A, SeqViewAsSeq[A]] =
    ArrayBuffer.newBuilder[A].mapResult(from)
}
