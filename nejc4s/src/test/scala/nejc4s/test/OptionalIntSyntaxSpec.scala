package nejc4s.test

import nejc4s.base.{AbsentInt, OptionalInt, PresentInt}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatestplus.junit.JUnitRunner

object OptionalIntSyntaxSpec {
  val present: PresentInt = PresentInt(1)
  val optional: OptionalInt = AbsentInt()
  val absent: OptionalInt = AbsentInt()
}

@RunWith(classOf[JUnitRunner])
class OptionalIntSyntaxSpec extends FreeSpec {
  import OptionalIntSyntaxSpec._
  import nejc4s.syntax.optional._

  "PresentInt" - {
    "`value` should work" in {
      present.value.toByte
    }
    "`unapply` should work" in {
      optional match {
        case PresentInt(a) => println(a)
        case _ =>
      }
    }
  }
  "OptionalInt" - {
    "`isAbsent` should work" in {
      optional.isAbsent
    }
    "`isPresent` should work" in {
      optional.isPresent
    }

    "`orElse` should work" in {
      optional.orElse(-1).toByte
    }
    "`orElse_` should work" in {
      optional.orElse_(-1).toByte
    }

    "`transform` should work" in {
      optional.transform(_ + 1).ifPresent(_.toByte.toString)
    }

    "`fold` should work" in {
      optional.fold(-1)(identity).toByte
    }

    "`flatTransform` should work" in {
      optional.flatTransform(_ => present).ifPresent(_.toByte)
    }

    "`filter` should work" in {
      optional.filter(_.toByte.isNaN).ifPresent(_.toByte)

      assert(absent.filter(_ => throw new RuntimeException()) == absent)
      assert(present.filter(_ => false) == absent)
      assert(present.filter(_ => true) == present)
    }
    "`filterNot` should work" in {
      optional.filterNot(_.toByte.isNaN).ifPresent(_.toByte)

      assert(absent.filterNot(_ => throw new RuntimeException()) == absent)
      assert(present.filterNot(_ => false) == present)
      assert(present.filterNot(_ => true) == absent)
    }

    "`contains` should work" in {
      optional.contains(-1)
    }
    "`exists` should work" in {
      optional.exists(_.toByte.isNaN)
    }
    "`forall` should work" in {
      optional.forall(_.toByte.isNaN)
    }

    "`ifPresent` should work" in {
      optional.ifPresent(_.toByte)
    }
    "`collect` should work" in {
      optional
        .collect {
          case -1 =>
            30
        }
        .ifPresent(_.toByte)
    }

    "`toOption` should work" in {
      optional.toOption
    }
  }
}
