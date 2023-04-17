package woowacourse.movie

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.model.PlayingTimes
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class PlayingTimesTest {

    @Test
    fun `평일 상영일은 오전 10시부터 두 시간 간격으로 상영 시간을 생성한다`() {
        val date = LocalDate.of(2023, 4, 11)
        val actual = PlayingTimes(date, date).times
        val expected = mapOf(date to makeTimes(date))
        assertEquals(actual, expected)
    }

    @Test
    fun `주말 상영일은 오전 9시부터 두 시간 간격으로 상영 시간을 생성한다`() {
        val date = LocalDate.of(2023, 4, 15)
        val actual = PlayingTimes(date, date).times
        val expected = mapOf(date to makeTimes(date))
        assertEquals(actual, expected)
    }

    private fun makeTimes(date: LocalDate) = buildList<LocalTime> {
        var hour = if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) 9 else 10
        while (hour < 24) {
            add(LocalTime.of(hour, 0))
            hour += 2
        }
    }
}
