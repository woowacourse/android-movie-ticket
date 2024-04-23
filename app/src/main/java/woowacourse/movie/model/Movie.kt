package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.IntStream

data class Movie(
    val id: Long,
    val title: String,
    @DrawableRes
    val thumbnailResourceId: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduction: String,
) {
    val dates: List<String>
        get() =
            IntStream.iterate(0) { i -> i + 1 }
                .limit(ChronoUnit.DAYS.between(startDate, endDate.plusDays(1)))
                .mapToObj { i -> startDate.plusDays(i.toLong()) }
                .collect(Collectors.toList())
                .map(LocalDate::toString)

    val times: List<String>
        get() =
            List(8) { num ->
                val add = num * 2
                FIRST_TIME.plusHours(add.toLong()).toString()
            }

    companion object {
        private val FIRST_TIME = LocalTime.of(9, 0)
    }
}
