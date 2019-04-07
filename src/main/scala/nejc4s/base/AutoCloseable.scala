package nejc4s.base

import nejc4s.base

object AutoCloseable {
  private[base]
  type Refined = AutoCloseable /*{
    override def close(): Unit
  }*/

  trait Proxy extends base.Proxy with Refined {
    protected
    override def delegate: AutoCloseable

    override def close(): Unit = delegate.close()
  }
}
