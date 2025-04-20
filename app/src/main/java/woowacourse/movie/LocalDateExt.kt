package woowacourse.movie

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toDotFormat(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
}
