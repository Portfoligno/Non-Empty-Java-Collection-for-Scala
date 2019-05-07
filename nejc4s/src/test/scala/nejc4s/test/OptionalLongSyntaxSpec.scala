package nejc4s.test

import nejc4s.base.{AbsentLong, OptionalLong, PresentLong}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatestplus.junit.JUnitRunner

object OptionalLongSyntaxSpec {
  val present: PresentLong = PresentLong(1)
  val optional: OptionalLong = AbsentLong()
  val absent: OptionalLong = AbsentLong()
}

@RunWith(classOf[JUnitRunner])
class OptionalLongSyntaxSpec extends FreeSpec {
  import OptionalLongSyntaxSpec._
  import nejc4s.syntax.optional._

  "Present" - {
    "`value` should work" in {
      present.value.toByte
    }
  }
  "Optional" - {
    "`isAbsent` should work" in {
      optional.isAbsent
    }
    "`isPresent` should work" in {
      optional.isPresent
    }

    "`orElse` should work" in {
      optional.orElse(-1L).toByte
    }
    "`orElse_` should work" in {
      optional.orElse_(-1L).toByte
    }

    "`transform` should work" in {
      optional.transform(_ + 1).ifPresent(_.toByte.toString)
    }

    "`fold` should work" in {
      optional.fold(-1L)(identity).toByte
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
          case -1L =>
            30L
        }
        .ifPresent(_.toByte)
    }

    "`toOption` should work" in {
      optional.toOption
    }
  }
}
