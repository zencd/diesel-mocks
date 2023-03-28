package kotlinSampler

import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    val DATE_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.from(ZoneOffset.UTC))

    val DATE_TIME_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC))
}
