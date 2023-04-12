package woowacourse.movie.domain

import java.time.LocalDate

object MovieTimesGenerator {
    fun getTimesByDate(date: LocalDate): List<MovieTime> {
        return when (date.dayOfWeek.value) {
            1, 2, 3, 4, 5 -> {
                (9..23 step 2).map(::MovieTime)
            }
            6, 7 -> {
                (10..24 step 2).map(::MovieTime)
            }
            else -> throw IllegalArgumentException()
        }
    }
}
