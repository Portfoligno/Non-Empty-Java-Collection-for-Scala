package nejc4s.syntax

import nejc4s.base.{Optional, Present}

trait OptionalSyntax {
  implicit def toPresentOps[A](present: Present[A]): PresentOps[A] =
    new PresentOps[A](present)

  implicit def toOptionalOps[A](optional: Optional[A]): OptionalOps[A] =
    new OptionalOps[A](optional)
}

class PresentOps[A](private val present: Present[A]) extends AnyVal {
  def value: A =
    present.get
}

class OptionalOps[A](private val optional: Optional[A]) extends AnyVal {
  def covary[B >: A]: Optional[B] =
    optional.asInstanceOf[Optional[B]]

  def isAbsent: Boolean =
    !optional.isPresent

  def orElse_[B >: A](default: => B): B =
    covary[B].orElseGet(() => default)

  def orElseNull[A1 >: A](implicit ev: Null <:< A1): A1 =
    covary[A1].orElse(ev(null))

  def transform[B](f: A => B): Optional[B] =
    optional.map(f(_))

  def fold[B](ifAbsent: => B)(f: A => B): B =
    if (!optional.isPresent) ifAbsent else f(optional.get)

  def flatTransform[B](f: A => Optional[B]): Optional[B] =
    optional.flatMap(f(_))

  def flatten[B](implicit ev: A <:< Optional[B]): Optional[B] =
    flatTransform(ev)

  def filterNot(p: A => Boolean): Optional[A] =
    optional.filter(!p(_))

  def contains[A1 >: A](element: A1): Boolean =
    exists(_ == element)

  def exists(p: A => Boolean): Boolean =
    fold(false)(p)

  def forall(p: A => Boolean): Boolean =
    fold(true)(p)

  def toOption[B >: A]: Option[B] =
    Present.unapply(optional)
}
