package nejc4s

object JavaObject {
  trait Refined extends JavaObject {
    //override def hashCode(): Int = super.hashCode()
    //override def equals(obj: Any): Boolean = super.equals(obj)
    //override def toString: String = super.toString
  }

  trait UnsafeProxy extends Refined {
    protected
    def delegate: JavaObject

    override def hashCode(): Int = delegate.hashCode()
    override def equals(obj: Any): Boolean = delegate.equals(obj)
    override def toString: String = delegate.toString
  }
}
