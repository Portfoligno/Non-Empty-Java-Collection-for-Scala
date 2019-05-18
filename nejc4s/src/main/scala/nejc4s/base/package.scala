package nejc4s

import java.{lang, util}

package object base {
  type AutoCloseable = lang.AutoCloseable
  type Optional[A] = util.Optional[A]
  type OptionalDouble = util.OptionalDouble
  type OptionalInt = util.OptionalInt
  type OptionalLong = util.OptionalLong
  type Spliterator[A] = util.Spliterator[A]
  type JavaBaseStream[A, S <: util.stream.BaseStream[A, S]] = util.stream.BaseStream[A, S]
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



  type AbsentInt <: OptionalInt with Absent.Tag

  object AbsentInt {
    private[base] trait Tag extends Any

    def unsafeFromOptionalInt(optional: OptionalInt): AbsentInt =
      if (!optional.isPresent) {
        optional.asInstanceOf[AbsentInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply(): AbsentInt =
      util.OptionalInt.empty.asInstanceOf[AbsentInt]

    def unapply(a: OptionalInt): Boolean =
      !a.isPresent
  }


  type AbsentLong <: OptionalLong with Absent.Tag

  object AbsentLong {
    def unsafeFromOptionalLong(optional: OptionalLong): AbsentLong =
      if (!optional.isPresent) {
        optional.asInstanceOf[AbsentLong]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply(): AbsentLong =
      util.OptionalLong.empty.asInstanceOf[AbsentLong]

    def unapply(a: OptionalLong): Boolean =
      !a.isPresent
  }


  type AbsentDouble <: OptionalDouble with Absent.Tag

  object AbsentDouble {
    def unsafeFromOptionalDouble(optional: OptionalDouble): AbsentDouble =
      if (!optional.isPresent) {
        optional.asInstanceOf[AbsentDouble]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply(): AbsentDouble =
      util.OptionalDouble.empty.asInstanceOf[AbsentDouble]

    def unapply(a: OptionalDouble): Boolean =
      !a.isPresent
  }


  type Absent[A] <: Optional[A] with Absent.Tag

  object Absent {
    private[base] trait Tag extends Any

    def unsafeFromOptional[A](optional: Optional[A]): Absent[A] =
      if (!optional.isPresent) {
        optional.asInstanceOf[Absent[A]]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply[A](): Absent[A] =
      util.Optional.empty.asInstanceOf[Absent[A]]

    def unapply(a: Optional[_]): Boolean =
      !a.isPresent
  }


  type PresentInt <: OptionalInt with Present.Tag

  object PresentInt {
    def unsafeFromOptionalInt(optional: OptionalInt): PresentInt =
      if (optional.isPresent) {
        optional.asInstanceOf[PresentInt]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply(a: Int): PresentInt =
      util.OptionalInt.of(a).asInstanceOf[PresentInt]

    def unapply(a: OptionalInt): OptionalInt.Opt =
      new OptionalInt.Opt(a)
  }


  type PresentLong <: OptionalLong with Present.Tag

  object PresentLong {
    def unsafeFromOptionalLong(optional: OptionalLong): PresentLong =
      if (optional.isPresent) {
        optional.asInstanceOf[PresentLong]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply(a: Long): PresentLong =
      util.OptionalLong.of(a).asInstanceOf[PresentLong]

    def unapply(a: OptionalLong): OptionalLong.Opt =
      new OptionalLong.Opt(a)
  }


  type PresentDouble <: OptionalDouble with Present.Tag

  object PresentDouble {
    def unsafeFromOptionalDouble(optional: OptionalDouble): PresentDouble =
      if (optional.isPresent) {
        optional.asInstanceOf[PresentDouble]
      } else {
        throw new IllegalArgumentException(String.valueOf(optional))
      }


    def apply(a: Double): PresentDouble =
      util.OptionalDouble.of(a).asInstanceOf[PresentDouble]

    def unapply(a: OptionalDouble): OptionalDouble.Opt =
      new OptionalDouble.Opt(a)
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
      util.Optional.of(a).asInstanceOf[Present[A]]

    def unapply[A](a: Optional[A]): Optional.Opt[A] =
      new Optional.Opt(a)
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


  type PositiveLong <: NaturalLong with PositiveLongX

  object PositiveLong {
    def fromLong(i: Long): Option[PositiveLong] =
      if (i > 0) {
        Some(i.asInstanceOf[PositiveLong])
      } else {
        None
      }

    def unsafeFromLong(i: Long): PositiveLong =
      if (i > 0) {
        i.asInstanceOf[PositiveLong]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type NaturalInt <: NaturalIntX with NaturalLong.Tag

  object NaturalInt {
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


  type PositiveIntX <: NaturalIntX with PositiveLongX.Tag

  object PositiveIntX {
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


  type PositiveLongX <: NaturalLongX with PositiveLongX.Tag

  object PositiveLongX {
    private[base] trait Tag extends Any

    def fromLong(i: Long): Option[PositiveLongX] =
      if (i > 0 || i == -1) {
        Some(i.asInstanceOf[PositiveLongX])
      } else {
        None
      }

    def unsafeFromLong(i: Long): PositiveLongX =
      if (i > 0 || i == -1) {
        i.asInstanceOf[PositiveLongX]
      } else {
        throw new IllegalArgumentException(String.valueOf(i))
      }
  }


  type NaturalIntX <: Int with NaturalLongX.Tag

  object NaturalIntX {
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
