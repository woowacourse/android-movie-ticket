package woowacourse.movie.domain.movie

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val id: Long,
    val title: String,
    val runningTime: Int,
    val screenPeriod: List<LocalDate>,
    val description: String,
    val imgResId: Int,
) {
    fun screenPeriodToString(): String {
        if (screenPeriod.size == 1) return screenPeriod[0].format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        return screenPeriod.joinToString(" ~ ") { it.format(DateTimeFormatter.ofPattern("yyyy.M.d")) }
    }
}
