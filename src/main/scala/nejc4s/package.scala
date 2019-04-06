import java.util

package object nejc4s {
  type JavaCollection[A] = JavaCollection.Base[A]
  type JavaIterator[A] = util.Iterator[A]
  type JavaList[A] = util.List[A]
  type JavaListIterator[A] = util.ListIterator[A]
  type JavaSpliterator[A] = util.Spliterator[A]
  type JavaStream[A] = util.stream.Stream[A]


  object PositiveInt {
    def fromInt(i: Int): Option[PositiveInt] =
      if (i > 0) {
        Some(i.asInstanceOf[PositiveInt])
      } else {
        None
      }

    def unsafeFromInt(i: Int): PositiveInt =
      if (i > 0) {
        i.asInstanceOf[PositiveInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }

  type PositiveInt <: NonNegativeInt with PonoInt


  object NonNegativeInt {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[NonNegativeInt] =
      if (i >= 0) {
        Some(i.asInstanceOf[NonNegativeInt])
      } else {
        None
      }

    def unsafeFromInt(i: Int): NonNegativeInt =
      if (i >= 0) {
        i.asInstanceOf[NonNegativeInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }

  type NonNegativeInt <: Int with NonNegativeInt.Tag


  object PonoInt {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[PositiveInt] =
      if (i > 0 || i == -1) {
        Some(i.asInstanceOf[PositiveInt])
      } else {
        None
      }

    def unsafeFromInt(i: Int): PositiveInt =
      if (i > 0 || i == -1) {
        i.asInstanceOf[PositiveInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }

  type PonoInt <: Int with PonoInt.Tag


  private[nejc4s] trait TrueTag extends Any
  type True <: Boolean with TrueTag
  val True: True = true.asInstanceOf[True]


  private[nejc4s] trait FalseTag extends Any
  type False <: Boolean with FalseTag
  val False: False = false.asInstanceOf[False]
}
