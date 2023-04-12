package woowacourse.movie.domain

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate

class MovieDate private constructor(
    val year: Int,
    val month: Int,
    val day: Int
) : Serializable {
    fun isDiscountDay(): Boolean = day in DISCOUNT_DAYS

    fun isWeekend(): Boolean {
        val dayOfWeek = LocalDate.of(year, month, day).dayOfWeek
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }

    fun isToday(): Boolean {
        val today = LocalDate.now()
        return today.compareTo(LocalDate.of(year, month, day)) == 0
    }

    companion object {
        private val DISCOUNT_DAYS = listOf(10, 20, 30)

        private fun LocalDate.max(other: LocalDate): LocalDate {
            if (this > other) return this
            return other
        }

        @JvmOverloads
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

        fun of(year: Int, month: Int, day: Int): MovieDate = MovieDate(year, month, day)
    }
}
