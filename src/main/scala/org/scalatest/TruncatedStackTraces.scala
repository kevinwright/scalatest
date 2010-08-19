package org.scalatest

import org.scalatest.Suite
import org.scalatest.AbstractSuite
import org.scalatest.StackDepthException

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
