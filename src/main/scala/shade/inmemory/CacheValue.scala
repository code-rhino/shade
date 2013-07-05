package shade.inmemory

import akka.util.{FiniteDuration, Duration}
import akka.util.duration._

case class CacheValue(
  value: Any,
  expiresTS: Long
)

object CacheValue {
  def apply(value: Any, exp: Duration): CacheValue =
    exp match {
      case finite: FiniteDuration =>
        CacheValue(value, System.currentTimeMillis() + finite.toMillis)
      case _ =>
        CacheValue(value, System.currentTimeMillis() + 365.days.toMillis)
    }
}