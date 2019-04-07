package nejc4s

object AutoCloseable {
  private[nejc4s]
  type Refined = AutoCloseable /*{
    override def close(): Unit
  }*/

  trait Proxy extends nejc4s.Proxy with Refined {
    protected
    override def delegate: AutoCloseable

    override def close(): Unit = delegate.close()
  }
}
