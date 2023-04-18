package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class RunningTimeTest {
    @Test
    fun `주말 영화 상영시간은 오전 9시부터 자정까지 두 시간 간격이다`() {
        val date = LocalDate.of(2023, 4, 16)
        val runningTime = RunningTime.runningTimes(
            date,
            isToday = { false }
        )
        val actual = runningTime.map { it.hour }
        val expected = (9 until 24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일 영화 상영시간은 오전 10시부터 자정까지 두 시간 간격이다`() {
        val date = LocalDate.of(2023, 4, 17)
        val runningTime = RunningTime.runningTimes(
            date,
            isWeekend = { false },
            isToday = { false },
        )
        val actual = runningTime.map { it.hour }
        val expected = (10 until 24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말 현재 시간이 10시면 영화 상영시간은 오전 11시부터 자정까지 두 시간 간격이다`() {
        val date = LocalDate.of(2023, 4, 16)
        val currentTime = LocalTime.of(10, 0)
        val runningTime = RunningTime.runningTimes(
            date,
            isWeekend = { true },
            isToday = { false },
            currentTime
        )
        val actual = runningTime.map { it.hour }
        val expected = (9 until 24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }
}
