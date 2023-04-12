package woowacourse.movie

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatScreenDate(): String {
    return format(DateTimeFormatter.ISO_LOCAL_DATE).replace('-', '.')
}
