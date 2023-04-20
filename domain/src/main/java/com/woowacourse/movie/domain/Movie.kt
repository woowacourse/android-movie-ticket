package com.woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Movie(
    val id: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
) {
    fun reserveMovie(
        reserveDateTime: LocalDateTime,
        ticket: Ticket,
        totalPrice: Int
    ): Reservation? {
        if (reserveDateTime.toLocalDate() !in getRunningDates(startDate)
        ) return null
        return Reservation(this, reserveDateTime, ticket, totalPrice)
    }

    fun getRunningDates(
        today: LocalDate = LocalDate.now()
    ): List<LocalDate> {
        if (today > endDate) return emptyList()
        return generateSequence(today max startDate) { it.plusDays(1) }
            .takeWhile { it <= endDate }
            .map { it }
            .toList()
    }

    fun getRunningTimes(
        date: LocalDate,
        currentTime: LocalTime = LocalTime.now()
    ): List<LocalTime> {
        val runningTimes = if (isWeekend(date)) weekendTimes else weekDayTimes
        val time = if (isToday(date)) currentTime else LocalTime.of(0, 0)

        return runningTimes.filter { it > time }
    }

    private fun isWeekend(date: LocalDate): Boolean =
        date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY

    private fun isToday(date: LocalDate): Boolean = date.compareTo(LocalDate.now()) == 0

    private infix fun LocalDate.max(other: LocalDate): LocalDate {
        if (this > other) return this
        return other
    }

    companion object {
        private val weekDayTimes = (10..24 step 2).map { hour -> LocalTime.of(hour % 24, 0, 0) }
        private val weekendTimes = (9 until 24 step 2).map { hour -> LocalTime.of(hour, 0, 0) }

        fun provideDummy(): List<Movie> = listOf(
            Movie(
                1,
                "해리 포터와 마법사의 돌",
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30),
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            ),
            Movie(
                2,
                "범죄 도시1",
                LocalDate.of(2023, 4, 12),
                LocalDate.of(2023, 5, 14),
                121,
                "통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!",
            )
        )
    }
}
