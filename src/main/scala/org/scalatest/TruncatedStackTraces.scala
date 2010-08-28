package org.scalatest

trait TruncatedStackTraces extends AbstractSuite { this: Suite =>

  abstract override def withFixture(test: NoArgTest) {
    try {
      super.withFixture(test)
    }
    catch {
      case e: StackDepthException =>
        val truncated = e.getStackTrace.drop(e.failedCodeStackDepth)
        e.setStackTrace(truncated)
        throw e
    }
  }
}
