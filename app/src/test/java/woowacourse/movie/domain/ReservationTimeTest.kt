package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ReservationTimeTest {
    @Test
    fun `주어진 날짜가 평일이라면 9시부터 영화가 시작한다`() {
        val times = ReservationTime(DayOfWeek.WEEKDAY).getScreeningTimes()
        assertEquals(times[0], "09:00")
    }

    @Test
    fun `주어진 날짜가 주말이라면 10시부터 영화가 시작한다`() {
        val times = ReservationTime(DayOfWeek.WEEKEND).getScreeningTimes()
        assertEquals(times[0], "10:00")
    }
}
