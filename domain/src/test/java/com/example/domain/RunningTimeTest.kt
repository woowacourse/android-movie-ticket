package com.example.domain

import com.example.domain.dateTime.RunningTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class RunningTimeTest {

    @Test
    fun `일요일이면 주말 상영시간대를 반환해준다`() {
        val date = LocalDate.of(2023, 4, 16)
        val runningTimeSetter = RunningTime()
        val actual = runningTimeSetter.getRunningTimes(date)
        val expected = (9 until 24 step 2).map { hour -> LocalTime.of(hour, 0, 0) }
        assertThat(actual).isEqualTo(expected)
    }
}
