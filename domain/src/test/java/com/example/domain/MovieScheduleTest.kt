package com.example.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieScheduleTest {
    @Test
    fun `상영시작 날짜와 상영 종료 날짜 사이의 날짜들을 가져온다`() {
        val movieSchedule =
            MovieSchedule(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5))
        val actual = movieSchedule.getScheduleDates()
        assertEquals(
            actual,
            listOf("2023-04-01", "2023-04-02", "2023-04-03", "2023-04-04", "2023-04-05")
        )
    }

    @Test
    fun `평일이면 9시부터 24시까지 2시간 단위로 상영한다`() {
        val movieSchedule =
            MovieSchedule(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5))
        val actual = movieSchedule.getScheduleTimes("2023-04-01")
        assertEquals(
            actual,
            listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
        )
    }

    @Test
    fun `주말이면 10시부터 24시까지 2시간 단위로 상영한다`() {
        val movieSchedule =
            MovieSchedule(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5))
        val actual = movieSchedule.getScheduleTimes("2023-04-03")
        assertEquals(
            actual,
            listOf("10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00", "24:00")
        )
    }
}
