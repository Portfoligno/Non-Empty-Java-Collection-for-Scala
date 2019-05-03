package nejc4s

import nejc4s.base.OptionalInt

package object syntax {
  object optional extends OptionalSyntax

  private[syntax]
  type OptionalInt_[X] = OptionalInt
}
