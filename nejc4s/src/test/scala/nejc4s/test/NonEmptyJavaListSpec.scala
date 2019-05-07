package nejc4s
package test

import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NonEmptyJavaListSpec extends FreeSpec {
  "NonEmptyJavaList" - {
    "`apply` should work" in {
      NonEmptyJavaList("a", "b", "c")
    }

    "`unapplySeq` should work" in {
      import syntax.seqView._

      NonEmptyJavaList.unapplySeq((0 until 10).asJava)

      (0 until 3).asJava match {
        case NonEmptyJavaList(0, 1, 2) =>
      }
    }
  }
}
