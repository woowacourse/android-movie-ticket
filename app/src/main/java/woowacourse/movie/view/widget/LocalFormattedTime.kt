package woowacourse.movie.view.widget

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalFormattedTime(val time: LocalTime) {
    override fun toString(): String {
        val dateFormat = DateTimeFormatter.ofPattern("HH:mm")
        return time.format(dateFormat)
    }
}
