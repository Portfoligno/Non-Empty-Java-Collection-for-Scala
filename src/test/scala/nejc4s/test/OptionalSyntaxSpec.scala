package nejc4s.test

import nejc4s.base.{Absent, Optional, Present, True}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner
import shapeless.test.illTyped

object OptionalSyntaxSpec {
  sealed trait SuperTag {
    def superDummy: Some[True] = Some(True)
  }
  sealed trait Tag extends SuperTag {
    def dummy: Some[True] = Some(True)
  }
  case object SuperTagInstance extends SuperTag
  case object TagInstance extends Tag

  val present: Present[Tag] = Present(TagInstance)
  val optional: Optional[Tag] = Absent()
}

@RunWith(classOf[JUnitRunner])
class OptionalSyntaxSpec extends FreeSpec {
  import OptionalSyntaxSpec._
  import nejc4s.syntax.optional._

  "Present" - {
    "`value` should work" in {
      present.value.dummy
    }
  }
  "Optional" - {
    "`covary` should be adapting type parameter" in {
      optional.covary: Optional[SuperTag]
    }

    "`isAbsent` should work" in {
      optional.isAbsent
    }
    "`isPresent` should work" in {
      optional.isPresent
    }

    "`orElse` should work partly" in {
      illTyped("""
        optional.orElse(SuperTagInstance).superDummy
      """)
      optional.orElse(TagInstance).dummy
    }
    "`orElse_` should work fully" in {
      optional.orElse_(SuperTagInstance).superDummy
      optional.orElse_(TagInstance).dummy
    }

    "`orElseNull` should work" in {
      optional.orElseNull: Tag
    }

    "`map` should not work" in {
      illTyped("""
        optional.map(_.dummy).transform(_.value)
      """)
    }
    "`transform` should work" in {
      optional.transform(_.dummy).transform(_.value)
    }

    "`fold` should work" in {
      optional.fold(SuperTagInstance: SuperTag)(identity).superDummy
    }

    "`flatMap` should not work" in {
      illTyped("""
        optional.flatMap(_ => present).transform(_.dummy)
      """)
    }
    "`flatTransform` should work" in {
      optional.flatTransform(_ => present).transform(_.dummy)
    }
    "`flatten` should work" in {
      optional.transform(_ => present).flatten.transform(_.dummy)
    }

    "`filter` should work" in {
      optional.filter(_.dummy.value).transform(_.dummy)
    }
    "`filterNot` should work" in {
      optional.filterNot(_.dummy.value).transform(_.dummy)
    }

    "`contains` should work" in {
      optional.contains(SuperTagInstance)
    }
    "`exists` should work" in {
      optional.exists(_.dummy.value)
    }
    "`forall` should work" in {
      optional.forall(_.dummy.value)
    }

    "`ifPresent` should work" in {
      optional.ifPresent(_.dummy)
    }

    "`toOption` should work" in {
      optional.toOption
    }
  }
}
