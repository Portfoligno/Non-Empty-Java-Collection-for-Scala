package nejc4s.base

import java.util.Comparator
import java.util.function.{Consumer, DoubleConsumer, IntConsumer, LongConsumer}
import java.{lang, util}

object Spliterator {
  type OfPrimitive[A, C, S <: OfPrimitive[A, C, S]] = util.Spliterator.OfPrimitive[A, C, S]
  type OfInt = util.Spliterator.OfInt
  type OfLong = util.Spliterator.OfLong
  type OfDouble = util.Spliterator.OfDouble


  trait Refined[A] extends Spliterator[A] {
    //override def tryAdvance(action: Consumer[_ >: A]): Boolean
    override def trySplit(): Spliterator.Refined[A]
    override def estimateSize: NaturalLong
    //override def characteristics: Int

    //override def forEachRemaining(action: Consumer[_ >: A]): Unit = super.forEachRemaining(action)
    override def getExactSizeIfKnown: NaturalLongX = NaturalLongX.unsafeFromLong(super.getExactSizeIfKnown)
    //override def hasCharacteristics(characteristics: Int): Boolean = super.hasCharacteristics(characteristics)
    //override def getComparator: Comparator[_ >: A] = super.getComparator
  }

  trait UnsafeProxy[A] extends Proxy with Refined[A] {
    protected
    override def delegate: Spliterator[A]

    override def tryAdvance(action: Consumer[_ >: A]): Boolean = delegate.tryAdvance(action)
    override def trySplit(): Spliterator.Refined[A] = new Spliterator.UnsafeWrapper(delegate.trySplit())
    override def estimateSize: NaturalLong = NaturalLong.unsafeFromLong(delegate.estimateSize)
    override def characteristics: Int = delegate.characteristics

    override def forEachRemaining(action: Consumer[_ >: A]): Unit = delegate.forEachRemaining(action)
    override def getExactSizeIfKnown: NaturalLongX = NaturalLongX.unsafeFromLong(delegate.getExactSizeIfKnown)
    override def hasCharacteristics(characteristics: Int): Boolean = delegate.hasCharacteristics(characteristics)
    override def getComparator: Comparator[_ >: A] = delegate.getComparator
  }

  class UnsafeWrapper[A](
    override protected val delegate: Spliterator[A]
  ) extends UnsafeProxy[A] with Spliterator[A]



  object OfInt {
    trait Refined extends OfInt with Spliterator.Refined[Integer] {
      override def trySplit(): Spliterator.OfInt.Refined
      //override def tryAdvance(action: IntConsumer): Boolean

      //override def forEachRemaining(action: IntConsumer): Unit = super.forEachRemaining(action)
      //override def tryAdvance(action: Consumer[_ >: Integer]): Boolean = super.tryAdvance(action)
      //override def forEachRemaining(action: Consumer[_ >: Integer]): Unit = super.forEachRemaining(action)
    }

    trait UnsafeProxy extends Spliterator.UnsafeProxy[Integer] with Refined {
      protected
      override def delegate: OfInt

      override def trySplit(): Spliterator.OfInt.Refined = new Spliterator.OfInt.UnsafeWrapper(delegate.trySplit())
      override def tryAdvance(action: IntConsumer): Boolean = delegate.tryAdvance(action)

      override def forEachRemaining(action: IntConsumer): Unit = delegate.forEachRemaining(action)
      override def tryAdvance(action: Consumer[_ >: Integer]): Boolean = delegate.tryAdvance(action)
      override def forEachRemaining(action: Consumer[_ >: Integer]): Unit = delegate.forEachRemaining(action)
    }

    class UnsafeWrapper(override protected val delegate: OfInt) extends UnsafeProxy with Spliterator[Integer]
  }


  object OfLong {
    trait Refined extends OfLong with Spliterator.Refined[lang.Long] {
      override def trySplit(): Spliterator.OfLong.Refined
      //override def tryAdvance(action: LongConsumer): Boolean

      //override def forEachRemaining(action: LongConsumer): Unit = super.forEachRemaining(action)
      //override def tryAdvance(action: Consumer[_ >: lang.Long]): Boolean = super.tryAdvance(action)
      //override def forEachRemaining(action: Consumer[_ >: lang.Long]): Unit = super.forEachRemaining(action)
    }

    trait UnsafeProxy extends Spliterator.UnsafeProxy[lang.Long] with Refined {
      protected
      override def delegate: OfLong

      override def trySplit(): Spliterator.OfLong.Refined =
        new Spliterator.OfLong.UnsafeWrapper(delegate.trySplit())
      override def tryAdvance(action: LongConsumer): Boolean = delegate.tryAdvance(action)

      override def forEachRemaining(action: LongConsumer): Unit = delegate.forEachRemaining(action)
      override def tryAdvance(action: Consumer[_ >: lang.Long]): Boolean = delegate.tryAdvance(action)
      override def forEachRemaining(action: Consumer[_ >: lang.Long]): Unit = delegate.forEachRemaining(action)
    }

    class UnsafeWrapper(override protected val delegate: OfLong) extends UnsafeProxy with Spliterator[lang.Long]
  }


  object OfDouble {
    trait Refined extends OfDouble with Spliterator.Refined[lang.Double] {
      override def trySplit(): Spliterator.OfDouble.Refined
      //override def tryAdvance(action: DoubleConsumer): Boolean

      //override def forEachRemaining(action: DoubleConsumer): Unit = super.forEachRemaining(action)
      //override def tryAdvance(action: Consumer[_ >: lang.Double]): Boolean = super.tryAdvance(action)
      //override def forEachRemaining(action: Consumer[_ >: lang.Double]): Unit = super.forEachRemaining(action)
    }

    trait UnsafeProxy extends Spliterator.UnsafeProxy[lang.Double] with Refined {
      protected
      override def delegate: OfDouble

      override def trySplit(): Spliterator.OfDouble.Refined =
        new Spliterator.OfDouble.UnsafeWrapper(delegate.trySplit())
      override def tryAdvance(action: DoubleConsumer): Boolean = delegate.tryAdvance(action)

      override def forEachRemaining(action: DoubleConsumer): Unit = delegate.forEachRemaining(action)
      override def tryAdvance(action: Consumer[_ >: lang.Double]): Boolean = delegate.tryAdvance(action)
      override def forEachRemaining(action: Consumer[_ >: lang.Double]): Unit = delegate.forEachRemaining(action)
    }

    class UnsafeWrapper(
      override protected val delegate: OfDouble
    ) extends UnsafeProxy with Spliterator[lang.Double]
  }
}
