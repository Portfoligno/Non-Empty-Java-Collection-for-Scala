package nejc4s.base

import java.util

object Optional {
  def fromNullable[A](a: A)(implicit ev: Null <:< A): Optional[A] =
    util.Optional.ofNullable(a)
}
