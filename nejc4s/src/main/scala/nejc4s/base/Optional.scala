package nejc4s.base

import java.util

object Optional {
  def fromNullable[A](a: A)(implicit ev: Null <:< A): Optional[A] =
    util.Optional.ofNullable(a)

  def fromOption[A](option: Option[A]): Optional[A] =
    option.fold(Absent(): Optional[A])(Present(_))


  class Opt[A](private val optional: Optional[A]) extends AnyVal {
    def get: A =
      optional.get

    def isEmpty: Boolean =
      !optional.isPresent
  }
}

object OptionalInt {
  def fromOption[A](option: Option[Int]): OptionalInt =
    option.fold(AbsentInt(): OptionalInt)(PresentInt(_))


  class Opt(private val optional: OptionalInt) extends AnyVal {
    def get: Int =
      optional.getAsInt

    def isEmpty: Boolean =
      !optional.isPresent
  }
}

object OptionalLong {
  def fromOption[A](option: Option[Long]): OptionalLong =
    option.fold(AbsentLong(): OptionalLong)(PresentLong(_))


  class Opt(private val optional: OptionalLong) extends AnyVal {
    def get: Long =
      optional.getAsLong

    def isEmpty: Boolean =
      !optional.isPresent
  }
}

object OptionalDouble {
  def fromOption[A](option: Option[Double]): OptionalDouble =
    option.fold(AbsentDouble(): OptionalDouble)(PresentDouble(_))


  class Opt(private val optional: OptionalDouble) extends AnyVal {
    def get: Double =
      optional.getAsDouble

    def isEmpty: Boolean =
      !optional.isPresent
  }
}
