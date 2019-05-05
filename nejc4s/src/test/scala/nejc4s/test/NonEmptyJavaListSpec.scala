package nejc4s.test

import nejc4s.NonEmptyJavaList
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NonEmptyJavaListSpec extends FreeSpec {
  "NonEmptyJavaList" - {
    "`apply` should work" in {
      NonEmptyJavaList("a", "b", "c")
    }
  }
}
