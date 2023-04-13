package woowacourse.movie.domain

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate

class MovieDate private constructor(
    val year: Int,
    val month: Int,
    val day: Int,
) : Discountable, Serializable {

    private constructor(date: LocalDate) : this(date.year, date.monthValue, date.dayOfMonth)

    fun isWeekend(): Boolean {
        val dayOfWeek = LocalDate.of(year, month, day).dayOfWeek
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }

    fun isToday(): Boolean {
        val today = LocalDate.now()
        return today.compareTo(LocalDate.of(year, month, day)) == 0
    }

    private fun isDiscountDay(): Boolean = day in DISCOUNT_DAYS

    override fun discount(money: Int): Int =
        if (isDiscountDay()) (money - money * DISCOUNT_PERCENT).toInt() else money

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDate

        if (year != other.year) return false
        if (month != other.month) return false
        if (day != other.day) return false

        return true
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + day
        return result
    }

    companion object {
        private val DISCOUNT_DAYS = listOf(10, 20, 30)
        private const val DISCOUNT_PERCENT = 0.1

        private infix fun LocalDate.max(other: LocalDate): LocalDate {
            if (this > other) return this
            return other
        }

        @JvmOverloads
        fun releaseDates(
            from: LocalDate,
            to: LocalDate,
            today: LocalDate = LocalDate.now(),
        ): List<MovieDate> {
            if (today > to) return emptyList()
            return generateSequence(today max from) { it.plusDays(1) }
                .takeWhile { it <= to }
                .map { MovieDate(it) }
                .toList()
        }
    }
}
