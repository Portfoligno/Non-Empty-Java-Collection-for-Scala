package nejc4s.syntax

import nejc4s.base.{AbsentInt, AbsentLong, Optional, OptionalInt, OptionalLong, Present, PresentInt, PresentLong}

trait OptionalSyntax {
  implicit def toPresentOps[A](present: Present[A]): PresentOps[A] =
    new PresentOps(present)

  implicit def toPresentIntOps[A](present: PresentInt): PresentIntOps =
    new PresentIntOps(present)

  implicit def toPresentLongOps[A](present: PresentLong): PresentLongOps =
    new PresentLongOps(present)

  implicit def toOptionalOps[A](optional: Optional[A]): OptionalOps[A] =
    new OptionalOps(optional)

  implicit def toOptionalIntOps[A](optional: OptionalInt): OptionalIntOps =
    new OptionalIntOps(optional)

  implicit def toOptionalLongOps[A](optional: OptionalLong): OptionalLongOps =
    new OptionalLongOps(optional)
}


class PresentOps[A](private val present: Present[A]) extends AnyVal {
  def value: A =
    present.get
}

class PresentIntOps(private val present: PresentInt) extends AnyVal {
  def value: Int =
    present.getAsInt
}

class PresentLongOps(private val present: PresentLong) extends AnyVal {
  def value: Long =
    present.getAsLong
}


private[syntax]
trait BaseOptionalOps[F[_], A, A0] extends Any {
  protected
  def optional: F[A]

  protected
  def isPresent: Boolean

  protected
  def get: A

  protected
  def fromOption[B <: A0](option: Option[B]): F[B]


  def covary[B >: A]: F[B] =
    optional.asInstanceOf[F[B]]

  def isAbsent: Boolean =
    !isPresent

  def orElse_[B >: A](default: => B): B =
    if (isAbsent) default else get

  def transform[B <: A0](f: A => B): F[B]

  def fold[B](ifAbsent: => B)(f: A => B): B =
    if (isAbsent) ifAbsent else f(get)

  def flatTransform[B <: A0](f: A => F[B]): F[B]

  def flatten[B <: A0](implicit ev: A <:< F[B]): F[B] =
    flatTransform(ev)

  def filterNot(p: A => Boolean): F[A]

  def contains[A1 >: A](element: A1): Boolean =
    exists(_ == element)

  def exists(p: A => Boolean): Boolean =
    fold(false)(p)

  def forall(p: A => Boolean): Boolean =
    fold(true)(p)

  def collect[B <: A0](pf: PartialFunction[A, B]): F[B] =
    flatTransform(pf.lift.andThen(fromOption))

  def orElseWith[B >: A <: A0](alternative: => F[B]): F[B] =
    if (isAbsent) alternative else covary

  def toOption[B >: A]: Option[B]
}


final class OptionalOps[A](
  protected
  override
  val optional: Optional[A]
) extends AnyVal with BaseOptionalOps[Optional, A, Any] {
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


final class OptionalIntOps(
  protected
  override
  val optional: OptionalInt
) extends AnyVal with BaseOptionalOps[OptionalInt_, Int, Int] {
  protected
  override
  def isPresent: Boolean =
    optional.isPresent

  protected
  override
  def get: Int =
    optional.getAsInt

  protected
  override
  def fromOption[B <: Int](option: Option[B]): OptionalInt =
    OptionalInt.fromOption(option)


  override
  def transform[B <: Int](f: Int => B): OptionalInt =
    fold(AbsentInt(): OptionalInt)(f.andThen(PresentInt(_)))

  override
  def flatTransform[B <: Int](f: Int => OptionalInt): OptionalInt =
    fold(AbsentInt(): OptionalInt)(f)

  def filter(p: Int => Boolean): OptionalInt =
    if (!exists(p)) AbsentInt() else optional

  override
  def filterNot(p: Int => Boolean): OptionalInt =
    if (forall(p)) AbsentInt() else optional

  override
  def toOption[B >: Int]: Option[B] =
    PresentInt.unapply(optional)
}

final class OptionalLongOps(
  protected
  override
  val optional: OptionalLong
) extends AnyVal with BaseOptionalOps[OptionalLong_, Long, Long] {
  protected
  override
  def isPresent: Boolean =
    optional.isPresent

  protected
  override
  def get: Long =
    optional.getAsLong

  protected
  override
  def fromOption[B <: Long](option: Option[B]): OptionalLong =
    OptionalLong.fromOption(option)


  override
  def transform[B <: Long](f: Long => B): OptionalLong =
    fold(AbsentLong(): OptionalLong)(f.andThen(PresentLong(_)))

  override
  def flatTransform[B <: Long](f: Long => OptionalLong): OptionalLong =
    fold(AbsentLong(): OptionalLong)(f)

  def filter(p: Long => Boolean): OptionalLong =
    if (!exists(p)) AbsentLong() else optional

  override
  def filterNot(p: Long => Boolean): OptionalLong =
    if (forall(p)) AbsentLong() else optional

  override
  def toOption[B >: Long]: Option[B] =
    PresentLong.unapply(optional)
}
