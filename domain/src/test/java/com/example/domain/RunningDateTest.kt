package com.example.domain

import com.example.domain.dateTime.RunningDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RunningDateTest {
    @Test
    fun `시작 상영 날짜와 마지막 상영 날짜 사이의 모든 날짜를 반환한다`() {
        val start = LocalDate.of(2023, 1, 4)
        val end = LocalDate.of(2023, 1, 8)
        val runningDateSetter = RunningDate()
        val actual = runningDateSetter.getRunningDates(start, end)
        val expected = listOf(
            LocalDate.of(2023, 1, 4),
            LocalDate.of(2023, 1, 5),
            LocalDate.of(2023, 1, 6),
            LocalDate.of(2023, 1, 7),
            LocalDate.of(2023, 1, 8),
        )
        assertThat(actual).isEqualTo(expected)
    }
}
