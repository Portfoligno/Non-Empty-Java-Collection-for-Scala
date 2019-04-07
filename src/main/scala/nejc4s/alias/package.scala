package nejc4s

package object alias {
  type Nejl[A] = NonEmptyJavaList[A]
  val Nejl: NonEmptyJavaList.type = NonEmptyJavaList
}
