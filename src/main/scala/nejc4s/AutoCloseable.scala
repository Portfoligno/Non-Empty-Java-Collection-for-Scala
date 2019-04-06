package nejc4s

object AutoCloseable {
  private[nejc4s]
  type Refined = AutoCloseable /*{
    override def close(): Unit
  }*/

  trait Proxy extends Refined with nejc4s.Proxy {
    protected
    override def delegate: AutoCloseable

    override def close(): Unit = delegate.close()
  }
}
