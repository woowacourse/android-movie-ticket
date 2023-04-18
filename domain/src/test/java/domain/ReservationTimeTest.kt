package domain

import domain.screeningschedule.ReservationTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationTimeTest {

    @Test
    fun `평일이면 시작 시간과 끝나는 시간 사이의 개수가 7개이다`() {
        val reservationTime = ReservationTime(DayOfWeek.WEEKDAY)
        assertThat(reservationTime.getIntervalTimes().size).isEqualTo(7)
    }

    @Test
    fun `주말이면 시작 시간과 끝나는 시간 사이의 개수가 8개이다`() {
        val reservationTime = ReservationTime(DayOfWeek.WEEKEND)
        assertThat(reservationTime.getIntervalTimes().size).isEqualTo(8)
    }
}
