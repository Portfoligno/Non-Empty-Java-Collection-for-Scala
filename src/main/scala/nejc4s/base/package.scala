package nejc4s

import java.{lang, util}
import java.util.Optional

package object base {
  type AutoCloseable = lang.AutoCloseable
  type Optional[A] = util.Optional[A]
  type Spliterator[A] = util.Spliterator[A]
  type JavaBaseStream[A, S <: JavaBaseStream[A, S]] = util.stream.BaseStream[A, S]
  type JavaCollection[A] = util.Collection[A]
  type JavaDoubleStream = util.stream.DoubleStream
  type JavaIntStream = util.stream.IntStream
  type JavaIterable[A] = lang.Iterable[A]
  type JavaIterator[A] = util.Iterator[A]
  type JavaPrimitiveIterator[A, C] = util.PrimitiveIterator[A, C]
  type JavaList[A] = util.List[A]
  type JavaListIterator[A] = util.ListIterator[A]
  type JavaLongStream = util.stream.LongStream
  type JavaStream[A] = util.stream.Stream[A]



  type False <: Boolean with FalseTag
  private[base] trait FalseTag extends Any
  val False: False = false.asInstanceOf[False]


  type True <: Boolean with TrueTag
  private[base] trait TrueTag extends Any
  val True: True = true.asInstanceOf[True]



  type Absent[A] <: Optional[A] with Absent.Tag

  object Absent {
    private[base] trait Tag extends Any

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
    private[base] trait Tag extends Any

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
    private[base] trait Tag extends Any

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


  type NaturalLong <: NaturalLongX with NaturalLong.Tag

  object NaturalLong {
    private[base] trait Tag extends Any

    def fromLong(i: Long): Option[NaturalLong] =
      if (i >= 0) {
        Some(i.asInstanceOf[NaturalLong])
      } else {
        None
      }

    def unsafeFromLong(i: Long): NaturalLong =
      if (i >= 0) {
        i.asInstanceOf[NaturalLong]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type PositiveIntX <: NaturalIntX with PositiveIntX.Tag

  object PositiveIntX {
    private[base] trait Tag extends Any

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
    private[base] trait Tag extends Any

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


  type NaturalLongX <: Long with NaturalLongX.Tag

  object NaturalLongX {
    private[base] trait Tag extends Any

    def fromLong(i: Long): Option[NaturalLongX] =
      if (i >= -1) {
        Some(i.asInstanceOf[NaturalLongX])
      } else {
        None
      }

    def unsafeFromLong(i: Long): NaturalLongX =
      if (i >= -1) {
        i.asInstanceOf[NaturalLongX]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }
}
