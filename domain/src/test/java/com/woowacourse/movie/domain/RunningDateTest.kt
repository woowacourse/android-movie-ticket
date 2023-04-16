package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RunningDateTest {
    @Test
    fun `상영일 범위 내에서 현재 날짜부터 마지막 상영일까지 목록을 반환한다`() {
        val runningDate = RunningDate().getRunningDates(
            startDate = LocalDate.of(2023, 4, 1),
            endDate = LocalDate.of(2023, 4, 28),
            today = LocalDate.of(2023, 4, 10)
        )
        val actual = runningDate.map { it.dayOfMonth }
        val expected = (10..28).toList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 상영일 이전이라면 모든 상영일 목록을 반환한다`() {
        val runningDates: List<LocalDate> = RunningDate().getRunningDates(
            startDate = LocalDate.of(2023, 4, 1),
            endDate = LocalDate.of(2023, 4, 28),
            today = LocalDate.of(2023, 3, 25)
        )

        val actual = runningDates.map { it.dayOfMonth }
        val expected = (1..28).toList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 상영일 이후라면 빈 목록을 반환한다`() {
        val runningDates: List<LocalDate> = RunningDate().getRunningDates(
            startDate = LocalDate.of(2023, 4, 1),
            endDate = LocalDate.of(2023, 4, 28),
            today = LocalDate.of(2023, 4, 29)
        )

        val actual = runningDates.map { it.dayOfMonth }
        val expected = emptyList<LocalDate>()
        assertThat(actual).isEqualTo(expected)
    }
}
