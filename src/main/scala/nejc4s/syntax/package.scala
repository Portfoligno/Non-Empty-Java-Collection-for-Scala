package nejc4s

import nejc4s.base.{OptionalInt, OptionalLong}

package object syntax {
  object optional extends OptionalSyntax


  private[syntax]
  type OptionalInt_[X] = OptionalInt

  private[syntax]
  type OptionalLong_[X] = OptionalLong
}
