package nejc4s.base

import java.util

object Optional {
  def fromNullable[A](a: A)(implicit ev: Null <:< A): Optional[A] =
    util.Optional.ofNullable(a)

  def fromOption[A](option: Option[A]): Optional[A] =
    option.fold(Absent(): Optional[A])(Present(_))
}

object OptionalInt {
  def fromOption[A](option: Option[Int]): OptionalInt =
    option.fold(AbsentInt(): OptionalInt)(PresentInt(_))
}

object OptionalLong {
  def fromOption[A](option: Option[Long]): OptionalLong =
    option.fold(AbsentLong(): OptionalLong)(PresentLong(_))
}
