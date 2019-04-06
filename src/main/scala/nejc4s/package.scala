import java.util

package object nejc4s {
  type JavaCollection[A] = util.Collection[A]
  type JavaIterable[A] = java.lang.Iterable[A]
  type JavaIterator[A] = util.Iterator[A]
  type JavaList[A] = util.List[A]
  type JavaListIterator[A] = util.ListIterator[A]
  type JavaSpliterator[A] = util.Spliterator[A]
  type JavaStream[A] = util.stream.Stream[A]


  type PositiveInt <: NaturalInt with PonoInt

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


  type NaturalInt <: NonoInt with NaturalInt.Tag

  object NaturalInt {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[NaturalInt] =
      if (i >= 0) {
        Some(i.asInstanceOf[NaturalInt])
      } else {
        None
      }

    def unsafeFromInt(i: Int): NaturalInt =
      if (i >= 0) {
        i.asInstanceOf[NaturalInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type PonoInt <: NonoInt with PonoInt.Tag

  object PonoInt {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[PonoInt] =
      if (i > 0 || i == -1) {
        Some(i.asInstanceOf[PonoInt])
      } else {
        None
      }

    def unsafeFromInt(i: Int): PonoInt =
      if (i > 0 || i == -1) {
        i.asInstanceOf[PonoInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type NonoInt <: Int with NonoInt.Tag

  object NonoInt {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[NonoInt] =
      if (i >= -1) {
        Some(i.asInstanceOf[NonoInt])
      } else {
        None
      }

    def unsafeFromInt(i: Int): NonoInt =
      if (i >= -1) {
        i.asInstanceOf[NonoInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type False <: Boolean with FalseTag
  private[nejc4s] trait FalseTag extends Any
  val False: False = false.asInstanceOf[False]


  type True <: Boolean with TrueTag
  private[nejc4s] trait TrueTag extends Any
  val True: True = true.asInstanceOf[True]
}
