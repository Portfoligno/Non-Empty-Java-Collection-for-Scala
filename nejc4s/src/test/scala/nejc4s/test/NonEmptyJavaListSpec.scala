package nejc4s
package test

import java.lang.reflect.InvocationTargetException

import nejc4s.base.{JavaList, Spliterator}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NonEmptyJavaListSpec extends FreeSpec {
  "NonEmptyJavaList" - {
    "`spliterator` should work" in {
      val l = NonEmptyJavaList("a", "b", "c")

      l.spliterator
      (l: NonEmptyJavaCollection[String]).spliterator
      (l: JavaList[String]).spliterator
      (l: JavaList.Refined[String]).spliterator

      l
        .getClass
        .getMethods
        .filter(m => classOf[Spliterator[_]].isAssignableFrom(m.getReturnType))
        .foreach { m =>
          try {
            m.invoke(l)
          } catch {
            case e: InvocationTargetException =>
              throw e.getTargetException
          }
        }
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
