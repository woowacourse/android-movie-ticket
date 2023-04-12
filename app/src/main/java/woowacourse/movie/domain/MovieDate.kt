package woowacourse.movie.domain

import java.time.LocalDate

class MovieDate private constructor(
    val year: Int,
    val month: Int,
    val day: Int
) {
    companion object {
        private fun LocalDate.max(other: LocalDate): LocalDate {
            if (this > other) return this
            return other
        }

        fun of(
            today: LocalDate = LocalDate.now(),
            from: LocalDate,
            to: LocalDate,
        ): List<MovieDate> {
            if (today > to) return emptyList()
            return generateSequence(today.max(from)) { it.plusDays(1) }
                .takeWhile { it <= to }
                .map { MovieDate(it.year, it.monthValue, it.dayOfMonth) }
                .toList()
        }
    }
}
