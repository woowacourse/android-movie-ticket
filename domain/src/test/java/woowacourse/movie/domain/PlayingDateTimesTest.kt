package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.domain.movie.PlayingDateTimes
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class PlayingDateTimesTest {

    @Test
    fun `평일 상영일은 오전 10시부터 두 시간 간격으로 상영 시간을 생성한다`() {
        val date = LocalDate.of(2023, 4, 11)
        val actual = PlayingDateTimes(date, date).times
        val expected = mapOf(date to makeTimes(date))
        assertEquals(actual, expected)
    }

    @Test
    fun `주말 상영일은 오전 9시부터 두 시간 간격으로 상영 시간을 생성한다`() {
        val date = LocalDate.of(2023, 4, 15)
        val actual = PlayingDateTimes(date, date).times
        val expected = mapOf(date to makeTimes(date))
        assertEquals(actual, expected)
    }

    private fun makeTimes(date: LocalDate) = buildList<LocalTime> {
        var hour = 10
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) hour = 9
        while (hour < 24) {
            add(LocalTime.of(hour, 0))
            hour += 2
        }
    }
}
