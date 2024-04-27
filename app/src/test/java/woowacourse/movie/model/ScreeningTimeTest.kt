package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.schedule.RegularTimeTable
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningTime
import woowacourse.movie.model.schedule.WeekdayTimeTable
import woowacourse.movie.model.schedule.WeekendTimeTable
import java.time.LocalDate

class ScreeningTimeTest {
    @Test
    fun `영화 상영 시간 범위는 오전 9시부터 자정까지다`() {
        val date = LocalDate.of(2024, 4, 26)
        assertThrows<IllegalArgumentException> { ScreeningTime.of(date, 6, 0) }
    }

    @Test
    fun `일정 시간 간격으로 영화를 상영한다`() {

        val date = LocalDate.of(2024, 4, 26) // friday
        val start = 9
        val end = 21
        val interval = 1

        val startTime = ScreeningTime.of(date, start, 0)
        val endTime = ScreeningTime.of(date, end, 0)

        val timeTable = RegularTimeTable(startTime, endTime, interval)

        val expected = (start..end step interval).map {
            ScreeningTime.of(date, it, 0)
        }
        val actual = timeTable.getScreeningTimes()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말엔 9시부터 24시까지 2시간 간격으로 영화를 상영한다`() {
        val date = LocalDate.of(2024, 4, 27) // saturday
        val screeningDate = ScreeningDate(date)

        val weekendTimeTable = WeekendTimeTable(screeningDate)

        val start = 9
        val end = 24
        val interval = 2
        val expected = (start..end step interval).map {
            ScreeningTime.of(date, it, 0)
        }
        val actual = weekendTimeTable.getScreeningTimes()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일엔 10시부터 24시까지 2시간 간격으로 영화를 상영한다`() {
        val date = LocalDate.of(2024, 4, 26) // friday
        val screeningDate = ScreeningDate(date)

        val weekdayTimeTable = WeekdayTimeTable(screeningDate)

        val start = 10
        val end = 24
        val interval = 2
        val expected = (start..end step interval).map {
            ScreeningTime.of(date, it, 0)
        }
        val actual = weekdayTimeTable.getScreeningTimes()
        assertThat(actual).isEqualTo(expected)
    }
}
