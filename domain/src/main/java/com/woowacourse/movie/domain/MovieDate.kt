package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountCondition
import java.time.DayOfWeek
import java.time.LocalDate

typealias MovieDateDomain = MovieDate

data class MovieDate(
    val year: Int,
    val month: Int,
    val day: Int,
) : DiscountCondition {
    constructor(date: LocalDate) : this(date.year, date.monthValue, date.dayOfMonth)

    fun isWeekend(): Boolean {
        val dayOfWeek = LocalDate.of(year, month, day).dayOfWeek
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }

    fun isToday(): Boolean {
        val today = LocalDate.now()
        return today.compareTo(LocalDate.of(year, month, day)) == 0
    }

    override fun isDiscount(): Boolean = day in DISCOUNT_DAYS

    companion object {
        private val DISCOUNT_DAYS = listOf(10, 20, 30)

        private infix fun LocalDate.max(other: LocalDate): LocalDate {
            if (this > other) return this
            return other
        }

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
