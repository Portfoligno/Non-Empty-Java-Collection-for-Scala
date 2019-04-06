import java.util
import java.util.Optional

package object nejc4s {
  type AutoCloseable = java.lang.AutoCloseable
  type Optional[A] = util.Optional[A]
  type Spliterator[A] = util.Spliterator[A]
  type JavaBaseStream[A, S <: JavaBaseStream[A, S]] = util.stream.BaseStream[A, S]
  type JavaCollection[A] = util.Collection[A]
  type JavaIterable[A] = java.lang.Iterable[A]
  type JavaIterator[A] = util.Iterator[A]
  type JavaList[A] = util.List[A]
  type JavaListIterator[A] = util.ListIterator[A]
  type JavaStream[A] = util.stream.Stream[A]



  type False <: Boolean with FalseTag
  private[nejc4s] trait FalseTag extends Any
  val False: False = false.asInstanceOf[False]


  type True <: Boolean with TrueTag
  private[nejc4s] trait TrueTag extends Any
  val True: True = true.asInstanceOf[True]



  type Absent[A] <: Optional[A] with Absent.Tag

  object Absent {
    private[nejc4s] trait Tag extends Any

    def unsafeFromOptional[A](optional: Optional[A]): Absent[A] =
      if (!optional.isPresent) {
        optional.asInstanceOf[Absent[A]]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply[A]: Absent[A] =
      Optional.empty().asInstanceOf[Absent[A]]

    def unapply(a: Optional[_]): Boolean =
      !a.isPresent
  }


  type Present[A] <: Optional[A] with Present.Tag

  object Present {
    private[nejc4s] trait Tag extends Any

    def unsafeFromOptional[A](optional: Optional[A]): Present[A] =
      if (optional.isPresent) {
        optional.asInstanceOf[Present[A]]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply[A](a: A): Present[A] =
      Optional.of(a).asInstanceOf[Present[A]]

    def unapply[A](a: Optional[A]): Option[A] =
      if (a.isPresent) Some(a.get) else None
  }



  type PositiveInt <: NaturalInt with PositiveIntX

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


  type NaturalInt <: NaturalIntX with NaturalInt.Tag

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


  type PositiveIntX <: NaturalIntX with PositiveIntX.Tag

  object PositiveIntX {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[PositiveIntX] =
      if (i > 0 || i == -1) {
        Some(i.asInstanceOf[PositiveIntX])
      } else {
        None
      }

    def unsafeFromInt(i: Int): PositiveIntX =
      if (i > 0 || i == -1) {
        i.asInstanceOf[PositiveIntX]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type NaturalIntX <: Int with NaturalIntX.Tag

  object NaturalIntX {
    private[nejc4s] trait Tag extends Any

    def fromInt(i: Int): Option[NaturalIntX] =
      if (i >= -1) {
        Some(i.asInstanceOf[NaturalIntX])
      } else {
        None
      }

    def unsafeFromInt(i: Int): NaturalIntX =
      if (i >= -1) {
        i.asInstanceOf[NaturalIntX]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }
}
