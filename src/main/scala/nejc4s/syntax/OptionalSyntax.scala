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


private[syntax]
trait BaseOptionalOps[F[_], A] extends Any {
  protected
  def optional: F[A]

  def covary[B >: A]: F[B] =
    optional.asInstanceOf[F[B]]


  protected
  def isPresent: Boolean

  def isAbsent: Boolean =
    !isPresent


  def orElse_[B >: A](default: => B): B

  def transform[B](f: A => B): F[B]


  protected
  def get: A

  def fold[B](ifAbsent: => B)(f: A => B): B =
    if (!isPresent) ifAbsent else f(get)


  def flatTransform[B](f: A => F[B]): F[B]

  def flatten[B](implicit ev: A <:< F[B]): F[B] =
    flatTransform(ev)

  def filterNot(p: A => Boolean): F[A]

  def contains[A1 >: A](element: A1): Boolean =
    exists(_ == element)

  def exists(p: A => Boolean): Boolean =
    fold(false)(p)

  def forall(p: A => Boolean): Boolean =
    fold(true)(p)


  protected
  def fromOption[B](option: Option[B]): F[B]

  def collect[B](pf: PartialFunction[A, B]): F[B] =
    flatTransform(pf.lift.andThen(fromOption))


  def orElseWith[B >: A](alternative: => F[B]): F[B] =
    if (!isPresent) alternative else covary

  def toOption[B >: A]: Option[B]
}


final class OptionalOps[A](
  protected
  override
  val optional: Optional[A]
) extends AnyVal with BaseOptionalOps[Optional, A] {
  protected
  override
  def isPresent: Boolean =
    optional.isPresent

  protected
  override
  def get: A =
    optional.get

  protected
  override
  def fromOption[B](option: Option[B]): Optional[B] =
    Optional.fromOption(option)


  override
  def orElse_[B >: A](default: => B): B =
    covary[B].orElseGet(() => default)

  def orElseNull[A1 >: A](implicit ev: Null <:< A1): A1 =
    covary[A1].orElse(ev(null))

  override
  def transform[B](f: A => B): Optional[B] =
    optional.map(f(_))

  override
  def flatTransform[B](f: A => Optional[B]): Optional[B] =
    optional.flatMap(f(_))

  override
  def filterNot(p: A => Boolean): Optional[A] =
    optional.filter(!p(_))

  override
  def toOption[B >: A]: Option[B] =
    Present.unapply(optional)
}
