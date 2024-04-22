package woowacourse.movie.domain.movie

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val id: Int,
    val title: String,
    val runningTime: Int,
    val screenPeriod: List<LocalDate>,
    val description: String,
    val imgSrc: Int,
) {
    fun screenPeriodToString(): String {
        if (screenPeriod.size == 1) return screenPeriod[0].format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        return screenPeriod.joinToString("~") { it.format(DateTimeFormatter.ofPattern("yyyy.M.d")) }
    }
}
