package woowacourse.movie

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalFormattedTime(val time: LocalTime) {
    override fun toString(): String {
        val dateFormat = DateTimeFormatter.ofPattern("hh:mm")
        return time.format(dateFormat)
    }
}
