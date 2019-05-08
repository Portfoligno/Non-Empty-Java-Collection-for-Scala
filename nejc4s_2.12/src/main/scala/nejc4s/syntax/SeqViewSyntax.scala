package nejc4s.syntax

import scala.collection.SeqView
import scala.collection.convert.{DecorateAsJava, DecorateAsScala}

private[nejc4s]
trait SeqViewSyntax extends DecorateAsJava with DecorateAsScala {
  implicit def toSeqViewOps[A](seqView: SeqView[A, _]): SeqViewOps[A] =
    new SeqViewOps(seqView)
}

private[nejc4s]
class SeqViewOps[A](private val seqView: SeqView[A, _]) extends AnyVal {
  def asSeq: Seq[A] =
    seqView
}
