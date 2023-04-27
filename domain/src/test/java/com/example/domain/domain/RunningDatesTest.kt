package com.example.domain.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.movie.domain.RunningDates
import java.time.LocalDate

class RunningDatesTest {
    @Test
    fun `시작 상영 날짜와 마지막 상영 날짜 사이의 모든 날짜를 반환한다`() {
        val start = LocalDate.of(2023, 1, 4)
        val end = LocalDate.of(2023, 1, 8)
        val runningDates = RunningDates(start, end)
        val actual = runningDates.getRunningDates()
        val expected = listOf(
            LocalDate.of(2023, 1, 4),
            LocalDate.of(2023, 1, 5),
            LocalDate.of(2023, 1, 6),
            LocalDate.of(2023, 1, 7),
            LocalDate.of(2023, 1, 8),
        )
        assertEquals(expected, actual)
    }
}
