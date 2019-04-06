package nejc4s

import java.util

object JavaCollection {
  trait Base[A] extends util.Collection[A] {
    def size: NonNegativeInt
    def iterator: JavaIterator[A]
    def spliterator: JavaSpliterator[A]
    def stream: JavaStream[A]
    def parallelStream: JavaStream[A]
  }
}
