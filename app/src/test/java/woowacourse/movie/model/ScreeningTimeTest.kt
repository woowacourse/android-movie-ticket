package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeTest {
    @Test
    fun `영화 상영 시간 범위는 오전 9시부터 자정까지다`() {
        val date = LocalDate.of(2024, 4, 26)
        val time = LocalTime.of(6, 0)
        assertThrows<IllegalArgumentException> { ScreeningTime(LocalDateTime.of(date, time)) }
    }

    @Test
    fun `일정 시간 간격으로 영화를 상영한다`() {

        val date = LocalDate.of(2024, 4, 26) // friday
        val start = 9
        val end = 21
        val interval = 1

        val startTime = ScreeningTime(LocalDateTime.of(date, LocalTime.of(start, 0)))
        val endTime = ScreeningTime(LocalDateTime.of(date, LocalTime.of(end, 0)))
        val timeTable = RegularTimeTable(startTime, endTime, interval)

        val expected = (start..end step interval).map {
            ScreeningTime(
                LocalDateTime.of(
                    date,
                    LocalTime.of(it, 0)
                )
            )
        }
        val actual = timeTable.getScreeningTimes()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말엔 9시부터 24시까지 2시간 간격으로 영화를 상영한다`() {
        val date = LocalDate.of(2024, 4, 27) // saturday

        val start = 9
        val end = 23
        val interval = 2

        val weekendTimeTable = WeekendTimeTable(date)

        val expected = (start..end step interval).map {
            ScreeningTime(
                LocalDateTime.of(
                    date,
                    LocalTime.of(it, 0)
                )
            )
        }
        val actual = weekendTimeTable.getScreeningTimes()
        assertThat(actual).isEqualTo(expected)
    }
}
