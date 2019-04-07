package nejc4s.base

trait Proxy {
  protected
  def delegate: Any

  override def hashCode(): Int = delegate.hashCode()
  override def equals(obj: Any): Boolean = delegate.equals(obj)
  override def toString: String = delegate.toString
}
