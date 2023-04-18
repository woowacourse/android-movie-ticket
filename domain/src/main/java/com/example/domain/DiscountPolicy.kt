package com.example.domain

import java.time.LocalDateTime
import java.time.LocalTime

enum class DiscountPolicy(private val discountCondition: DiscountCondition) {
    MOVIE_DAY(DayDiscountCondition(getMovieDays())) {
        override fun calculateDiscountFee(initialFee: Money, peopleCount: Int): Money =
            initialFee / getMovieDaysDiscountRate()
    },
    SCREENING_TIME(
        ScreeningTimeDiscountCondition(
            listOf(getEarlyMorningDiscountTimeRange(), getNightDiscountTimeRange())
        )
    ) {
        override fun calculateDiscountFee(initialFee: Money, peopleCount: Int): Money =
            Money(peopleCount * getScreeningTimeDiscountAmount())
    };

    protected abstract fun calculateDiscountFee(initialFee: Money, peopleCount: Int): Money

    private fun getDiscountFee(
        dateTime: LocalDateTime,
        initialFee: Money,
        peopleCount: Int
    ): Money =
        if (discountCondition.isSatisfiedBy(dateTime)) calculateDiscountFee(initialFee, peopleCount)
        else Money(0)

    companion object {
        fun getDiscountedFee(dateTime: LocalDateTime, initialFee: Money, peopleCount: Int): Money {
            var totalFee = initialFee
            values().forEach {
                totalFee -= it.getDiscountFee(dateTime, initialFee, peopleCount)
            }
            return totalFee
        }
    }
}

private fun getMovieDays(): List<Int> = listOf(10, 20, 30)

private fun getMovieDaysDiscountRate(): Int = 10

private fun getEarlyMorningDiscountTimeRange() = TimeRange(LocalTime.MIN, LocalTime.of(11, 0))

private fun getNightDiscountTimeRange() = TimeRange(LocalTime.of(20, 0), LocalTime.MAX)

private fun getScreeningTimeDiscountAmount(): Int = 2000
