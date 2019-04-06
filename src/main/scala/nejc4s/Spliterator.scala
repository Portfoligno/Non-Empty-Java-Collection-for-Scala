package nejc4s

import java.util

object Spliterator {
  type OfDouble = util.Spliterator.OfDouble
  type OfInt = util.Spliterator.OfInt
  type OfLong = util.Spliterator.OfLong
  type OfPrimitive[A, C, S <: OfPrimitive[A, C, S]] = util.Spliterator.OfPrimitive[A, C, S]
}
